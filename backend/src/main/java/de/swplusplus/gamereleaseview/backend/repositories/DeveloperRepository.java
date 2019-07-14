package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface DeveloperRepository extends CrudRepository<Developer, String> {
}
