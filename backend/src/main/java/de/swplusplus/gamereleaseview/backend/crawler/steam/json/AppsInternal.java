package de.swplusplus.gamereleaseview.backend.crawler.steam.json;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppsInternal {
    List<AppId> apps = new ArrayList<>();
}
