package de.swplusplus.gamereleaseview.backend.model;

import de.swplusplus.gamereleaseview.backend.crawler.steam.json.AppDetail;
import de.swplusplus.gamereleaseview.backend.crawler.steam.json.PriceOverview;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class GameRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long platformInternalId;
    @Temporal(TemporalType.DATE)
    Date releaseDateRangeFrom;
    @Temporal(TemporalType.DATE)
    private Date releaseDateRangeTo;
    @ManyToOne
    private Game game;
    @ManyToOne
    private Platform platform;
    private Boolean releaseDateUnknown;
    private String originalReleaseDateString;

    private Boolean isFree;
    private String currency;
    private Long price;

    public GameRelease() {
    }

    public GameRelease(Date releaseDate, Game game, Platform platform, Long platformInternalId) {
        this.releaseDateRangeFrom = releaseDate;
        this.releaseDateRangeTo = releaseDate;
        this.game = game;
        this.platform = platform;
        this.platformInternalId = platformInternalId;
    }

    public GameRelease(Pair<Date, Date> releaseDateRange, Game game, Platform platform, Long platformInternalId) {
        this.platformInternalId = platformInternalId;
        this.releaseDateRangeFrom = releaseDateRange.getFirst();
        this.releaseDateRangeTo = releaseDateRange.getSecond();
        this.game = game;
        this.platform = platform;
    }

    public LocalDate getReleaseDateRangeFromLocal() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(getReleaseDateRangeFrom().getTime()), ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getReleaseDateRangeToLocal() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(getReleaseDateRangeTo().getTime()), ZoneId.systemDefault()).toLocalDate();
    }

    public void updateFromAppDetail(AppDetail appDetail) {
        isFree = appDetail.getData().getIs_free();
        PriceOverview pr = appDetail.getData().getPrice_overview();
        if (pr != null) {
            currency = pr.getCurrency();
            price = pr.getFinal_();
        }
        setPlatformInternalId(appDetail.getData().getSteam_appid());
    }
}
