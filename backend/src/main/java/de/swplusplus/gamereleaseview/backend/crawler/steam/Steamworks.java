package de.swplusplus.gamereleaseview.backend.crawler.steam;

import de.swplusplus.gamereleaseview.backend.model.Game;
import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import de.swplusplus.gamereleaseview.backend.model.Platform;
import de.swplusplus.gamereleaseview.backend.repositories.GameReleaseRepository;
import de.swplusplus.gamereleaseview.backend.repositories.GameRepository;
import de.swplusplus.gamereleaseview.backend.repositories.PlatformRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    GameRepository gameRepository;
    GameReleaseRepository gameReleaseRepository;
    PlatformRepository platformRepository;

    @Autowired
    public Steamworks(GameRepository gameRepository, GameReleaseRepository gameReleaseRepository, PlatformRepository platformRepository) {
        this.gameRepository = gameRepository;
        this.gameReleaseRepository = gameReleaseRepository;
        this.platformRepository = platformRepository;
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void Crawl() {
        RestTemplate restTemplate = new RestTemplate();
        AppList appIds = restTemplate.getForObject("http://api.steampowered.com/ISteamApps/GetAppList/v00002", AppList.class);

        Iterable<Game> knownGames = gameRepository.findAll(); // todo: use findAllForPlatform instead
        Map<Long, String> knownGamesMap = new HashMap<>();
        knownGames.forEach(kg -> knownGamesMap.put(kg.getPlatformInternalId(), kg.getName()));

        appIds.getApplist().getApps().removeIf(app -> knownGamesMap.containsKey(app.getAppid()));

        Platform platform = platformRepository.findById(PLATFORM_NAME).get();
        if (platform == null) {
            platform = new Platform(PLATFORM_NAME);
            platformRepository.save(platform);
        }

//        for (AppId app : appIds.getApplist().getApps()) {
//            AppDetail appDetail = restTemplate.getForObject("https://store.steampowered.com/api/appdetails?appids=458630&filters=release_date", AppDetail.class);
//            Game game = new Game(app.getName());
//            game.setPlatformInternalId(app.getAppid());
//            GameRelease gr = new GameRelease(new GregorianCalendar(appDetail.getData().getReleaseData().getDate()));
//        }
    }
}
