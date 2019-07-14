package de.swplusplus.gamereleaseview.backend.crawler.steam.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Achievements {
    Long total;
    List<Achievement> highlighted;
}
