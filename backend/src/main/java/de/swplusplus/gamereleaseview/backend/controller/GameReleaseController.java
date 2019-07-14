package de.swplusplus.gamereleaseview.backend.controller;

import de.swplusplus.gamereleaseview.backend.model.Category;
import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import de.swplusplus.gamereleaseview.backend.repositories.GameReleaseRepository;
import org.openapitools.model.InlineResponse200;
import org.openapitools.model.InlineResponse200Languages;
import org.openapitools.model.InlineResponse200Releases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.openapitools.RFC3339DateFormat;

@RestController
@RequestMapping("/releases")
public class GameReleaseController {

    private final String DATE_FORMAT = "yyyy-MM-dd";

    final GameReleaseRepository gameReleaseRepository;

    @Autowired
    public GameReleaseController(GameReleaseRepository gameReleaseRepository) {
        this.gameReleaseRepository = gameReleaseRepository;
    }

    // processes requests like:
    // http://localhost:8080/releases?from=2019-06-17&to=2019-12-31
    @RequestMapping(value = {"", "/"},
                    method = RequestMethod.GET)
    public InlineResponse200 Releases(@RequestParam(name = "from", required = false) @DateTimeFormat(pattern=DATE_FORMAT) Optional<String> dateFrom,
                                      @RequestParam(name = "to", required = false) @DateTimeFormat(pattern=DATE_FORMAT) Optional<String> dateTo,
                                      @RequestParam(name = "with_unknown", required = false) Optional<Boolean> withUnknown,
                                      @RequestParam(name = "exact_dates_only", required = false) Optional<Boolean> exactDatesOnly) {
        Date from = new Date();
        Date to = null;
        boolean unknown = withUnknown.orElse(true);
        boolean exact = exactDatesOnly.orElse(false);

        if (dateFrom.isPresent()) {
            try {
                from = new SimpleDateFormat(DATE_FORMAT, Locale.UK).parse(dateFrom.get());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (dateTo.isPresent()) {
            try {
                to = new SimpleDateFormat(DATE_FORMAT, Locale.UK).parse(dateTo.get());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<GameRelease> gameReleases = null;
        if (to != null) {
            gameReleases = gameReleaseRepository.findByReleaseDateRangeFromBetweenOrReleaseDateRangeToBetween(from, to);
        } else {
            gameReleases = gameReleaseRepository.findByReleaseDateRangeFromAfterOrReleaseDateRangeToAfter(from);
        }

        InlineResponse200 res = new InlineResponse200();
        for(GameRelease release : gameReleases) {
            if (unknown || !release.getReleaseDateUnknown()) {
                if (!exact || release.getReleaseDateRangeFrom().equals(release.getReleaseDateRangeTo())) {
                    InlineResponse200Releases resi = new InlineResponse200Releases();
                    resi.id(release.getId()).name(release.getGame().getName())
                            .dateFrom(release.getReleaseDateRangeFromLocal())
                            .dateTo(release.getReleaseDateRangeToLocal())
                            .unknownReleaseDate((release.getReleaseDateUnknown() == null ? false : release.getReleaseDateUnknown()))
                            .originalReleaseString(release.getOriginalReleaseDateString())
                            .requiredAge(release.getGame().getRequiredAge())
                            .shortDescription(release.getGame().getShortDescription())
                            .primaryImage(release.getGame().getLinkToImage())
                            .website(release.getGame().getLinkToWebsite())
                            .backgroundImage(release.getGame().getLinkToBackgroundImage());

                    release.getGame().getCategories().forEach((cat) -> {resi.addCategoriesItem(cat.getDescription());});
                    release.getGame().getDevelopers().forEach((dev) -> {resi.addDevelopersItem(dev.getName());});
                    release.getGame().getGenres().forEach((genre)-> {resi.addGenresItem(genre.getName());});
                    release.getGame().getLanguages().forEach((lang)-> {resi.addLanguagesItem(new InlineResponse200Languages().language(lang.getLanguage()).ui(lang.getUi()).spoken(lang.getSpoken()).subtitles(lang.getSubtitles()));});

                    res.addReleasesItem(resi);
                }
            }
        }
        return res;
    }

}
