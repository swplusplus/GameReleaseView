package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "name")
public class Publisher {
    @Id
    String name;
}
