package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public
class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long platformInternalId;
    private String name;

    @OneToMany(mappedBy = "game")
    private Set<GameRelease> gameReleases = new HashSet<>();

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }
}
