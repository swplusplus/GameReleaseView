package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class GameRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long platformInternalId;
    private Date releaseDateRangeFrom;
    private Date releaseDateRangeTo;
    @ManyToOne
    private Game game;
    @ManyToOne
    private Platform platform;

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
}
