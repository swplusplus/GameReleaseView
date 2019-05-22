package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GameRepository extends CrudRepository<Game, Long> {

    public Iterable<Game> findAllForPlatform(String platform);
}
