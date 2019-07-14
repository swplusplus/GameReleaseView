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
    static final int shortDescLen = 1024;
    static final int fullDescLen = 131072;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "game", fetch=FetchType.EAGER)
    private Set<GameRelease> gameReleases = new HashSet<>();

    private Long requiredAge;
    @Column(length = shortDescLen)
    private String shortDescription;
    @Column(length = fullDescLen)
    private String detailedDescription;
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Language> languages = new HashSet<>();
    private String linkToImage;
    private String linkToWebsite;
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Developer> developers = new HashSet<>();
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Publisher> publishers = new HashSet<>();
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Category> categories = new HashSet<>();
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Genre> genres = new HashSet<>();
    @OneToMany(fetch=FetchType.EAGER)
    private Set<Screenshot> screenshots = new HashSet<>();
    private Long recommendations;
    private String linkToBackgroundImage;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public void assignGameRelease(GameRelease rel) {
        for (GameRelease r : gameReleases) {
            if (r.getPlatform() == rel.getPlatform()) {
                gameReleases.remove(r);
                break;
            }
        }
        gameReleases.add(rel);
    }

    public void addDeveloper(Developer dev) {
        developers.add(dev);
    }
    public void addPublisher(Publisher pub) {
        publishers.add(pub);
    }
    public void addCategory(Category cat) {
        categories.add(cat);
    }
    public void addGenre(Genre gen) {
        genres.add(gen);
    }
    public void addScreenshot(Screenshot scr) {
        screenshots.add(scr);
    }

    public void setShortDescription(String desc){
        if (desc.length() > shortDescLen) {
            shortDescription = desc.substring(0, shortDescLen);
        } else {
            shortDescription = desc;
        }
    }
    public void setDetailedDescription(String desc) {
        if (desc.length() > fullDescLen) {
            detailedDescription = desc.substring(0, fullDescLen);
        } else {
            detailedDescription = desc;
        }
    }
}
