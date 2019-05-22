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


@Service
public class Steamworks {
    private final String PLATFORM_NAME = "Steam";
    private final int NUMBER_OF_REQUESTS_BEFORE_WAIT = 200;
    private final int WAIT_FOR_SECONDS = 301;
    private final int COMING_SOON_MONTH = 12 * 5;

    private final Logger logger = LoggerFactory.getLogger(Steamworks.class);

    private GameRepository gameRepository;
    private GameReleaseRepository gameReleaseRepository;
    private PlatformRepository platformRepository;
    private BlacklistRepository blacklistRepository;

    @Autowired
    public Steamworks(GameRepository gameRepository, GameReleaseRepository gameReleaseRepository, PlatformRepository platformRepository, BlacklistRepository blacklistRepository) {
        this.gameRepository = gameRepository;
        this.gameReleaseRepository = gameReleaseRepository;
        this.platformRepository = platformRepository;
        this.blacklistRepository = blacklistRepository;
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void Crawl() {
        Platform platform = ensurePlatform();

        RestTemplate restTemplate = new RestTemplate();
        AppList appIds = restTemplate.getForObject("http://api.steampowered.com/ISteamApps/GetAppList/v00002", AppList.class);

        filterKnownApps(appIds);
        filterBlacklistedApps(appIds);

        logger.info("processing {} new apps from {}", appIds.getApplist().getApps().size(), PLATFORM_NAME);

        int numberRequest = 0;

        for (AppId app : appIds.getApplist().getApps()) {
            Game game = ensureGame(app);
            final String qString = String.format("https://store.steampowered.com/api/appdetails?appids=%d&filters=release_date", app.getAppid());
            final ParameterizedTypeReference<Map<Long, AppDetail>> ptr = new ParameterizedTypeReference<Map<Long, AppDetail>>(){};
            final ResponseEntity<Map<Long, AppDetail>> re = restTemplate.exchange(qString, HttpMethod.GET, null, ptr);
            AppDetail appDetail = re.getBody().entrySet().iterator().next().getValue();
            if (appDetail.isSuccess()) {
                String strDate = appDetail.getData().getRelease_date().getDate();
                try {
                    GameRelease gr = new GameRelease(parseDate(strDate, appDetail.getData().getRelease_date().isComingSoon()), game, platform, app.getAppid());
                    gr.setPlatformInternalId(app.getAppid());
                    game.getGameReleases().add(gr);
                    gameRepository.save(game);
                    gameReleaseRepository.save(gr);
                } catch (ParseException e) {
                    gameRepository.save(game);
                }
            } else {
                Blacklist bl = new Blacklist(game, platform, app.getAppid());
                gameRepository.save(game);
                blacklistRepository.save(bl);
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

    private Pair<Date, Date> parseDate(String strDate, boolean isFlaggedComingSoon) throws ParseException {

        try {
            strDate = strDate.trim();

            List<String> singleDateFormats = new ArrayList<String>() {{
                add(new String("d MMM. yyyy"));
                add(new String("d MMM, yyyy"));
                add(new String("MMM, d\'th\', yyyy"));
                add(new String("yyyy"));
            }};
            for (String format : singleDateFormats) {
                try {
                    Date d = new SimpleDateFormat(format, new Locale("C")).parse(strDate);
                    return Pair.of(d, d);
                } catch (ParseException e) {
                }
            }

            if (strDate.toLowerCase().contains("coming soon")
                    || strDate.toLowerCase().contains("expected soon!")
                    || (strDate.trim().isEmpty() && isFlaggedComingSoon)) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.clear(Calendar.HOUR);
                calendar.clear(Calendar.MINUTE);
                calendar.clear(Calendar.SECOND);
                calendar.clear(Calendar.MILLISECOND);
                Date d1 = calendar.getTime();
                calendar.add(Calendar.MONTH, COMING_SOON_MONTH);
                Date d2 = calendar.getTime();
                return Pair.of(d1, d2);
            }

            if (strDate.toLowerCase().matches("q[1234] \\d{4}")) {
                Map<String, Integer> startDates = new HashMap<>() {{
                    put("q1", 0);
                    put("q2", 3);
                    put("q3", 6);
                    put("q4", 9);
                }};
                try {
                    Date d1 = new SimpleDateFormat("yyyy").parse(strDate.substring(3));
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(d1);
                    calendar.add(Calendar.MONTH, startDates.get(strDate.toLowerCase().substring(0, 2)));
                    d1 = calendar.getTime();
                    calendar.add(Calendar.MONTH, 3);
                    Date d2 = calendar.getTime();
                    return Pair.of(d1, d2);
                } catch (ParseException e) {
                }
            }

        } catch (Exception e) {
        }
        logger.error("unhandled date format: " + strDate);
        throw new ParseException("unhandled date format: " + strDate, 0);
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
