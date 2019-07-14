package de.swplusplus.gamereleaseview.backend.crawler.steam.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppData {
    String type;
    String name;
    Long steam_appid;
    Long required_age;
    Boolean is_free;
    List<Long> dlc;
    String detailed_description;
    String about_the_game;
    String short_description;
    String supported_languages;
    String header_image;
    String website;

    // ATTENTION, these are difficult to parse, becaucse:
    //            "pc_requirements": {
    //        "minimum": "<strong>Minimum:<\/strong><br><ul class=\"bb_ul\"><li><strong>Betriebssystem:<\/strong> Windows 7 or later<br><\/li><li><strong>Prozessor:<\/strong> Intel Core 2 Duo E6320 (2*1866) or equivalent<br><\/li><li><strong>Arbeitsspeicher:<\/strong> 2 GB RAM<br><\/li><li><strong>Grafik:<\/strong> GeForce 7600 GS (512 MB) or equivalent<br><\/li><li><strong>Speicherplatz:<\/strong> 2 GB verf\u00fcgbarer Speicherplatz<\/li><\/ul>"
    //    },
    //            "mac_requirements": {
    //        "minimum": "<strong>Minimum:<\/strong><br><ul class=\"bb_ul\"><li><strong>Betriebssystem:<\/strong> OS 10.6+<br><\/li><li><strong>Arbeitsspeicher:<\/strong> 2 GB RAM<br><\/li><li><strong>Speicherplatz:<\/strong> 2 GB verf\u00fcgbarer Speicherplatz<\/li><\/ul>"
    //    },
    //            "linux_requirements": [],
    //                                 ^^^^
//    Requirements pc_requirements;
//    Requirements mac_requirements;
//    Requirements linux_requirements;
    List<String> developers;
    List<String> publishers;
    PriceOverview price_overview;
    // packages left out
    // List<String> package_groups; not a list of strings
    List<Category> categories;
    List<Genre> genres;
    List<Screenshot> screenshots;
    // movies left out
    Recommendations recommendations;
    ReleaseData release_date;
    SupportInfo support_info;
    String background;
    ContentDescriptors content_descriptors;
}
