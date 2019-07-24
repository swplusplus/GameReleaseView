package de.swplusplus.gamereleaseview.backend;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext appCtx) {
        try {
            // should be C:\Projects\swplusplus\GameReleaseView\backend\out\production\classes
            File pwd = new File(getClass().getResource("/").toURI());

            File projectDir = pwd.getParentFile();
            while(!projectDir.getName().equals("backend")){
                projectDir = projectDir.getParentFile();
            }
            String dbPath = new File(projectDir, "db/GameReleaseViewDB").getAbsolutePath();
            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", "jdbc:h2:" + dbPath + ";DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
            MapPropertySource mapPropertySource = new MapPropertySource("db-props", props);
            appCtx.getEnvironment().getPropertySources().addFirst(mapPropertySource);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }}