package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Game game;

    private Long platformInternalId;
    @ManyToOne
    private Platform platform;

    public Blacklist() {
    }

    public Blacklist(Game game, Platform platform, Long platformInternalId) {
        this.game = game;
        this.platform = platform;
        this.platformInternalId = platformInternalId;
    }
}
