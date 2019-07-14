package de.swplusplus.gamereleaseview.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Language {
    @Id
    @GeneratedValue
    Long id;
    String language;
    Boolean ui;
    Boolean spoken;
    Boolean subtitles;
}
