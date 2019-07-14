package de.swplusplus.gamereleaseview.backend.crawler.steam.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PriceOverview {
    String currency;
    Long initial;
    @JsonProperty("final")
    Long final_;
    Long discount_percent;
    String initial_formatted;
    String final_formatted;
}
