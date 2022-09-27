package com.kakaopay.membership.point.repository;

import com.kakaopay.membership.point.entity.Point;
import com.kakaopay.membership.point.entity.PointPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, PointPK> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    Optional<Point> findById(PointPK pointPK);
}
