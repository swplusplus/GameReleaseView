package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public
class Platform {
    @Id
    private String name;

    public Platform() {
    }

    public Platform(String name) {
        this.name = name;
    }
}
