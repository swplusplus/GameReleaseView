package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface LanguageRepository extends CrudRepository<Language, Long> {
    Optional<Language> findByLanguageAndUiAndSpokenAndSubtitles(String name, boolean ui, boolean spoken, boolean subtitles);
}
