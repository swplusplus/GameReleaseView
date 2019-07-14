package de.swplusplus.gamereleaseview.backend.crawler.steam;

import de.swplusplus.gamereleaseview.backend.model.Language;
import de.swplusplus.gamereleaseview.backend.repositories.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

// example string:
// "English<strong>*</strong>, French, Italian, German, Spanish - Spain, Portuguese - Brazil<br><strong>*</strong>languages with full audio support"

@Component
public class LanguageParser {
    private final Logger logger = LoggerFactory.getLogger(DateParser.class);
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageParser(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    Set<Language> parseLanguages(String toParse) {
        Set<Language> languages = new HashSet<>();

        String[] ls = toParse.split("\\s*,\\s*");
        for(String s : ls) {
            boolean ui = true; // assume always UI ??
            boolean spoken;
            boolean subtitles = false; // no idea
            if (s.contains("<br")) {
                s = s.substring(0, s.indexOf("<br"));
            }
            if (s.contains("<strong>*</strong>")) {
                spoken = true;
                s = s.replace("<strong>*</strong>","");
            } else {
                spoken = false;
            }
            final String s1 = s;
            Language l = languageRepository.findByLanguageAndUiAndSpokenAndSubtitles(s, ui, spoken, subtitles).orElseGet(() -> {
                Language language = new Language();
                language.setLanguage(s1);
                language.setUi(ui);
                language.setSpoken(spoken);
                language.setSubtitles(subtitles);
                languageRepository.save(language);
                return language;
            });

            languages.add(l);
        }
        return languages;
    }
}
