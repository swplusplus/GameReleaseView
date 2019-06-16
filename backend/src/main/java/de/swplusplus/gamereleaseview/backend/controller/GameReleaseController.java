package de.swplusplus.gamereleaseview.backend.controller;

import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import de.swplusplus.gamereleaseview.backend.repositories.GameReleaseRepository;
import org.openapitools.model.InlineResponse200;
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
                                      @RequestParam(name = "to", required = false) @DateTimeFormat(pattern=DATE_FORMAT) Optional<String> dateTo) {
        Date from = new Date();
        Date to = null;

        if (dateFrom.isPresent()) {
            try {
                from = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(dateFrom.get());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (dateTo.isPresent()) {
            try {
                to = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(dateTo.get());
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
            InlineResponse200Releases resi = new InlineResponse200Releases();
            resi.id(release.getId()).name(release.getGame().getName())
                    .dateFrom(release.getReleaseDateRangeFromLocal())
                    .dateTo(release.getReleaseDateRangeToLocal())
                    .unknownReleaseDate((release.getReleaseDateUnknown() == null ? false : release.getReleaseDateUnknown()))
                    .originalReleaseString(release.getOriginalReleaseDateString());
            res.addReleasesItem(resi);
        }
        return res;
    }

}
