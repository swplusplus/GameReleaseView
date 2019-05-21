package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class GameRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date releaseDate;
    @ManyToOne
    private Game game;
    @ManyToOne
    private Platform platform;

    public GameRelease(Date releaseDate, Game game, Platform platform) {
        this.releaseDate = releaseDate;
        this.game = game;
        this.platform = platform;
    }
}
