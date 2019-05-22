package de.swplusplus.gamereleaseview.backend.crawler.steam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppDetail {
    String appid;
    AppDetailData data;

    @Data
    class AppDetailData {
        boolean success;
        ReleaseData releaseData;

        @Data
        class ReleaseData {
            boolean comingSoon;
            String date;
        }
    }
}
