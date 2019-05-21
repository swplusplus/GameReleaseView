package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PlatformRepository extends CrudRepository<Platform, Long> {
}
