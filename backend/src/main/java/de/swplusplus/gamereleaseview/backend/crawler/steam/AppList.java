package de.swplusplus.gamereleaseview.backend.crawler.steam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppList {
    AppsInternal applist = new AppsInternal();

}

@Data
class AppsInternal {
    List<AppId> apps = new ArrayList<>();
}

@Data
class AppId {
    Long appid;
    String name;
}
