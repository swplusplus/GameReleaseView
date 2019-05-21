package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GameReleaseRepository extends CrudRepository<GameRelease, Long> {
}
