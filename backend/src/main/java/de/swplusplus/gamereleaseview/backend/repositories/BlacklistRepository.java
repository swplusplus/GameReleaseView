package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.Blacklist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlacklistRepository extends CrudRepository<Blacklist, Long> {
    public List<Blacklist> findAllByPlatformName(String platform);
}
