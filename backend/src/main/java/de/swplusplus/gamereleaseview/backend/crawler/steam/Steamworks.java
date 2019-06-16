package de.swplusplus.gamereleaseview.backend.crawler.steam;

import de.swplusplus.gamereleaseview.backend.model.Blacklist;
import de.swplusplus.gamereleaseview.backend.model.Game;
import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import de.swplusplus.gamereleaseview.backend.model.Platform;
import de.swplusplus.gamereleaseview.backend.repositories.BlacklistRepository;
import de.swplusplus.gamereleaseview.backend.repositories.GameReleaseRepository;
import de.swplusplus.gamereleaseview.backend.repositories.GameRepository;
import de.swplusplus.gamereleaseview.backend.repositories.PlatformRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


//@Service
public class Steamworks {
    private final String PLATFORM_NAME = "Steam";
    private final int NUMBER_OF_REQUESTS_BEFORE_WAIT = 200;
    private final int WAIT_FOR_SECONDS = 301;


    private final Logger logger = LoggerFactory.getLogger(Steamworks.class);

    private GameRepository gameRepository;
    private GameReleaseRepository gameReleaseRepository;
    private PlatformRepository platformRepository;
    private BlacklistRepository blacklistRepository;

    private final DateParser dateParser;

    @Autowired
    public Steamworks(GameRepository gameRepository, GameReleaseRepository gameReleaseRepository, PlatformRepository platformRepository, BlacklistRepository blacklistRepository, DateParser dateParser) {
        this.gameRepository = gameRepository;
        this.gameReleaseRepository = gameReleaseRepository;
        this.platformRepository = platformRepository;
        this.blacklistRepository = blacklistRepository;
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

        logger.info("processing {} new apps from {}", appIds.getApplist().getApps().size(), PLATFORM_NAME);

        int numberRequest = 0;

        for (AppId app : appIds.getApplist().getApps()) {
            Game game = ensureGame(app);
            final String qString = String.format("https://store.steampowered.com/api/appdetails?appids=%d&filters=release_date", app.getAppid());
            final ParameterizedTypeReference<Map<Long, AppDetail>> ptr = new ParameterizedTypeReference<>(){};
            final ResponseEntity<Map<Long, AppDetail>> re = restTemplate.exchange(qString, HttpMethod.GET, null, ptr);
            if (re.getBody() != null) {
                AppDetail appDetail = re.getBody().entrySet().iterator().next().getValue();
                if (appDetail.isSuccess()) {
                    String strDate = appDetail.getData().getRelease_date().getDate();
                    try {
                        GameRelease gr = new GameRelease(dateParser.parseDate(strDate, appDetail.getData().getRelease_date().isComingSoon()), game, platform, app.getAppid());
                        gr.setPlatformInternalId(app.getAppid());
                        game.getGameReleases().add(gr);
                        gameRepository.save(game);
                        gameReleaseRepository.save(gr);
                    } catch (ParseException e) {
                        // ignore
                    }
                } else {
                    Blacklist bl = new Blacklist(game, platform, app.getAppid());
                    blacklistRepository.save(bl);
                }
            }
            ++numberRequest;
            if (numberRequest%NUMBER_OF_REQUESTS_BEFORE_WAIT==0) {
                logger.info("waiting for {} seconds every {} requests (now on request {})...", WAIT_FOR_SECONDS, NUMBER_OF_REQUESTS_BEFORE_WAIT, numberRequest);
                try {
                    TimeUnit.SECONDS.sleep(WAIT_FOR_SECONDS);
                } catch (InterruptedException e) {
                    logger.error(e.toString());
                }
            }
        }
    }

    private void filterKnownApps(AppList appIds) {
        //List<GameRelease> knownGames = gameReleaseRepository.findAllByPlatformName(PLATFORM_NAME);
        Iterable<GameRelease> knownGames = gameReleaseRepository.findAll();
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
