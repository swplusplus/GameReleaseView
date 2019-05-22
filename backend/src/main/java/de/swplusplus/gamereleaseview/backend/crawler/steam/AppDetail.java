package de.swplusplus.gamereleaseview.backend.crawler.steam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppDetail {
    boolean success;
    AppData data = new AppData();
}

@Data
class AppData {
    ReleaseData release_date = new ReleaseData();
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ReleaseData {
    boolean comingSoon;
    String date;
}