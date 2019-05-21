package de.swplusplus.gamereleaseview.backend.bootstrap;

import de.swplusplus.gamereleaseview.backend.model.Game;
import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import de.swplusplus.gamereleaseview.backend.model.Platform;
import de.swplusplus.gamereleaseview.backend.repositories.GameReleaseRepository;
import de.swplusplus.gamereleaseview.backend.repositories.GameRepository;
import de.swplusplus.gamereleaseview.backend.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PlatformRepository platformRepository;
    private GameReleaseRepository gameReleaseRepository;
    private GameRepository gameRepository;

    @Autowired
    public DevBootstrap(GameReleaseRepository gameReleaseRepository, GameRepository gameRepository, PlatformRepository platformRepository) {
        this.gameReleaseRepository = gameReleaseRepository;
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Platform p = new Platform("Steam");
        platformRepository.save(p);

        Game g = new Game("disfated");

        GameRelease gr = new GameRelease(new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime(), g, p);
        g.getGameReleases().add(gr);

        gameRepository.save(g);
        gameReleaseRepository.save(gr);
    }
}
