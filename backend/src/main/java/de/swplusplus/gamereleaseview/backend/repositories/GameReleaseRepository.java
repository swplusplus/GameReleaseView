package de.swplusplus.gamereleaseview.backend.repositories;

import de.swplusplus.gamereleaseview.backend.model.GameRelease;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface GameReleaseRepository extends CrudRepository<GameRelease, Long> {
    public List<GameRelease> findByPlatformName(String platform);

    public default List<GameRelease> findByReleaseDateRangeFromAfterOrReleaseDateRangeToAfter(Date from) {
        return findByReleaseDateRangeFromAfterOrReleaseDateRangeToAfter(from, from);
    }
    public List<GameRelease> findByReleaseDateRangeFromAfterOrReleaseDateRangeToAfter(Date from, Date from1);

    public default List<GameRelease> findByReleaseDateRangeFromBetweenOrReleaseDateRangeToBetween(Date from, Date to) {
        return findByReleaseDateRangeFromBetweenOrReleaseDateRangeToBetween(from, to, from, to);
    }
    public List<GameRelease> findByReleaseDateRangeFromBetweenOrReleaseDateRangeToBetween(Date from, Date to, Date fromFrom, Date toTo);

    public default List<GameRelease> findByReleaseDateRangeFromBeforeAndReleaseDateRangeToBefore(Date to){
        return findByReleaseDateRangeFromBeforeAndReleaseDateRangeToBefore(to, to);
    }
    public List<GameRelease> findByReleaseDateRangeFromBeforeAndReleaseDateRangeToBefore(Date to, Date to1);
}
