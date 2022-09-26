package com.kakaopay.membership.point.repository;

import com.kakaopay.membership.point.repository.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, String> {

}
