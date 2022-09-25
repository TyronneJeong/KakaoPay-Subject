package com.kakaopay.memebership.point.repository;

import com.kakaopay.memebership.point.model.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, String> {

}
