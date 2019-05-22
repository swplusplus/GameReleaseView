package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "name")
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
