package de.swplusplus.gamereleaseview.backend.crawler.steam;

import de.swplusplus.gamereleaseview.backend.crawler.steam.json.AppData;
import de.swplusplus.gamereleaseview.backend.crawler.steam.json.AppDetail;
import de.swplusplus.gamereleaseview.backend.crawler.steam.json.AppId;
import de.swplusplus.gamereleaseview.backend.crawler.steam.json.AppList;
import de.swplusplus.gamereleaseview.backend.crawler.steam.json.Category;
import de.swplusplus.gamereleaseview.backend.model.*;
import de.swplusplus.gamereleaseview.backend.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

// SteamAPI
// http://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001
// http://api.steampowered.com/ISteamApps/GetAppList/v00002
// https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#Known_methods
// https://store.steampowered.com/api/appdetails?appids=459820&filters=basic,release_date
// https://store.steampowered.com/api/appdetails?appids=458630&filters=release_date
// https://store.steampowered.com/tagdata/populartags/german
// https://store.steampowered.com/tagdata/gettaggames/de/21?name=Abenteur&cc=DE&l=german


@Service
public class Steamworks {
    private final String PLATFORM_NAME = "Steam";
    private final int NUMBER_OF_REQUESTS_BEFORE_WAIT = 200;
    private final int WAIT_FOR_SECONDS = 301;


    private final Logger logger = LoggerFactory.getLogger(Steamworks.class);

    private final GameRepository gameRepository;
    private final GameReleaseRepository gameReleaseRepository;
    private final PlatformRepository platformRepository;
    private final BlacklistRepository blacklistRepository;
    private final DeveloperRepository developerRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final GenreRepository genreRepository;
    private final ScreenshotRepository screenshotRepository;
    private final LanguageParser languageParser;
    private final DateParser dateParser;


    public Steamworks(GameRepository gameRepository, GameReleaseRepository gameReleaseRepository, PlatformRepository platformRepository, BlacklistRepository blacklistRepository, DeveloperRepository developerRepository, PublisherRepository publisherRepository, CategoryRepository categoryRepository, GenreRepository genreRepository, ScreenshotRepository screenshotRepository, LanguageParser languageParser, DateParser dateParser) {
        this.gameRepository = gameRepository;
        this.gameReleaseRepository = gameReleaseRepository;
        this.platformRepository = platformRepository;
        this.blacklistRepository = blacklistRepository;
        this.developerRepository = developerRepository;
        this.publisherRepository = publisherRepository;
        this.categoryRepository = categoryRepository;
        this.genreRepository = genreRepository;
        this.screenshotRepository = screenshotRepository;
        this.languageParser = languageParser;
        this.dateParser = dateParser;
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void Crawl() {
        Platform platform = ensurePlatform();

        RestTemplate restTemplate = new RestTemplate();
        AppList appIds = restTemplate.getForObject("http://api.steampowered.com/ISteamApps/GetAppList/v00002", AppList.class);

        if (appIds == null) {
            logger.error("could not retrieve applist from steam");
            return;
        }

        filterKnownApps(appIds);
        filterBlacklistedApps(appIds);

        logger.info("processing {} unreleased apps from {}", appIds.getApplist().getApps().size(), PLATFORM_NAME);

        int numberRequest = 0;

        for (AppId app : appIds.getApplist().getApps()) {
            Game game = ensureGame(app);
            try {
                final String qString = String.format("https://store.steampowered.com/api/appdetails?appids=%d", app.getAppid());
                final ParameterizedTypeReference<Map<Long, AppDetail>> ptr = new ParameterizedTypeReference<>() {};
                final ResponseEntity<Map<Long, AppDetail>> re = restTemplate.exchange(qString, HttpMethod.GET, null, ptr);
                if (re.getBody() != null) {
                    AppDetail appDetail = re.getBody().entrySet().iterator().next().getValue();
                    if (appDetail.isSuccess()) {
                        String strDate = appDetail.getData().getRelease_date().getDate();
                        try {
                            Pair<Date, Date> releaseDate = dateParser.parseDate(strDate, appDetail.getData().getRelease_date().isComing_soon());
                            GameRelease gr = ensureGameRelease(releaseDate, game, platform, app.getAppid());
                            gr.setReleaseDateUnknown(dateParser.isComingSoonButUnknown(strDate, appDetail.getData().getRelease_date().isComing_soon()));
                            gr.setOriginalReleaseDateString(strDate);
                            gr.updateFromAppDetail(appDetail);
                            game.assignGameRelease(gr);
                            // only store details for unreleased games to save some space
                            final Date now = new Date();
                            if (now.compareTo(releaseDate.getSecond()) < 0) {
                                updateGameFromAppDetails(game, appDetail);
                            }
                            gameRepository.save(game);
                            gameReleaseRepository.save(gr);
                        } catch (ParseException e) {
                            logger.error(app.toString() + " ### " + e.toString());
                        }
                    } else {
                        Blacklist bl = new Blacklist(game, platform, app.getAppid());
                        blacklistRepository.save(bl);
                    }
                }
                ++numberRequest;
                if (numberRequest % NUMBER_OF_REQUESTS_BEFORE_WAIT == 0) {
                    logger.info("waiting for {} seconds every {} requests (now on request {})...", WAIT_FOR_SECONDS, NUMBER_OF_REQUESTS_BEFORE_WAIT, numberRequest);
                    try {
                        TimeUnit.SECONDS.sleep(WAIT_FOR_SECONDS);
                    } catch (InterruptedException e) {
                        logger.error(e.toString());
                    }
                }
            } catch (Exception e) {
                logger.error("BLACKLISTING " + app.toString() + " ### " + e.toString());
//                Blacklist bl = new Blacklist(game, platform, app.getAppid());
//                blacklistRepository.save(bl);
            }
        }
    }

    private void updateGameFromAppDetails(Game game, AppDetail appDetail) {
        AppData d = appDetail.getData();
        if (d != null) {
            game.setRequiredAge(d.getRequired_age());
            game.setShortDescription(d.getShort_description());
            game.setDetailedDescription(d.getDetailed_description());
            game.setLinkToImage(d.getHeader_image());
            game.setLinkToWebsite(d.getWebsite());

            if (d.getSupported_languages() != null) {
                game.setLanguages(languageParser.parseLanguages(d.getSupported_languages()));
            }
            if (d.getDevelopers() != null) {
                for (String dev : d.getDevelopers()) {
                    Developer developer = developerRepository.findById(dev).orElseGet(() -> {
                        Developer developer1 = new Developer();
                        developer1.setName(dev);
                        developerRepository.save(developer1);
                        return developer1;
                    });
                    game.addDeveloper(developer);
                }
            }
            if (d.getPublishers() != null) {
                for (String pub : d.getPublishers()) {
                    Publisher publisher = publisherRepository.findById(pub).orElseGet(() -> {
                        Publisher publisher1 = new Publisher();
                        publisher1.setName(pub);
                        publisherRepository.save(publisher1);
                        return publisher1;
                    });
                    game.addPublisher(publisher);
                }
            }
            if (d.getCategories() != null) {
                for (Category cat : d.getCategories()) {
                    de.swplusplus.gamereleaseview.backend.model.Category category = categoryRepository.findByDescription(cat.getDescription()).orElseGet(() -> {
                        de.swplusplus.gamereleaseview.backend.model.Category category1 = new de.swplusplus.gamereleaseview.backend.model.Category();
                        category1.setDescription(cat.getDescription());
                        categoryRepository.save(category1);
                        return category1;
                    });
                    game.addCategory(category);
                }
            }
            if (d.getGenres() != null) {
                for (de.swplusplus.gamereleaseview.backend.crawler.steam.json.Genre gen : d.getGenres()) {
                    Genre genre = genreRepository.findById(gen.getDescription()).orElseGet(() -> {
                        Genre genre1 = new Genre();
                        genre1.setName(gen.getDescription());
                        genreRepository.save(genre1);
                        return genre1;
                    });
                    game.addGenre(genre);
                }
            }
            if (d.getScreenshots() != null) {
                for (de.swplusplus.gamereleaseview.backend.crawler.steam.json.Screenshot scr : d.getScreenshots()) {
                    Screenshot screenshot = screenshotRepository.findById(scr.getPath_thumbnail()).orElseGet(() -> {
                        Screenshot screenshot1 = new Screenshot();
                        screenshot1.setLinkThumbnail(scr.getPath_thumbnail());
                        screenshot1.setLinkFull(scr.getPath_full());
                        screenshotRepository.save(screenshot1);
                        return screenshot1;
                    });
                    game.addScreenshot(screenshot);
                }
            }
            if (d.getRecommendations() != null) {
                game.setRecommendations(d.getRecommendations().getTotal());
            }
            game.setLinkToBackgroundImage(d.getBackground());
        }
    }

    private GameRelease ensureGameRelease(Pair<Date, Date> releaseDate, Game game, Platform platform, Long appid) {
        GameRelease gr = gameReleaseRepository.findByGameIdAndPlatformNameAndPlatformInternalId(game.getId(), platform.getName(), appid);
        if (gr == null) {
            gr = new GameRelease(releaseDate, game, platform, appid);
        }
        return gr;
    }

    private void filterKnownApps(AppList appIds) {
        //List<GameRelease> knownGames = gameReleaseRepository.findAllByPlatformName(PLATFORM_NAME);
        Iterable<GameRelease> knownGames = gameReleaseRepository.findByReleaseDateRangeFromBeforeAndReleaseDateRangeToBefore(new Date());
        Map<Long, String> knownGamesMap = new HashMap<>();
        knownGames.forEach(kg -> knownGamesMap.put(kg.getPlatformInternalId(), kg.getGame().getName()));
        appIds.getApplist().getApps().removeIf(app -> knownGamesMap.containsKey(app.getAppid()));
    }

    private void filterBlacklistedApps(AppList appIds) {
        //List<Blacklist> knownGames = blacklistRepository.findAllByPlatformName(PLATFORM_NAME);
        Iterable<Blacklist> knownGames = blacklistRepository.findAll();
        Map<Long, String> knownGamesMap = new HashMap<>();
        knownGames.forEach(kg -> knownGamesMap.put(kg.getPlatformInternalId(), kg.getGame().getName()));
        appIds.getApplist().getApps().removeIf(app -> knownGamesMap.containsKey(app.getAppid()));
    }

    private Game ensureGame(AppId app) {
        Game game = gameRepository.findByName(app.getName());
        if (game == null) {
            game = new Game(app.getName());
            gameRepository.save(game);
        }
        return game;
    }

    private Platform ensurePlatform() {
        Platform platform = platformRepository.findById(PLATFORM_NAME).orElse(null);
        if (platform == null) {
            platform = new Platform(PLATFORM_NAME);
            platformRepository.save(platform);
        }
        return platform;
    }
}
