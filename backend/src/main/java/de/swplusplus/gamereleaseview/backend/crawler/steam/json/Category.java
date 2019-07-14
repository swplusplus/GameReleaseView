package de.swplusplus.gamereleaseview.backend.crawler.steam.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Category {
    Long id;
    String description;
}
