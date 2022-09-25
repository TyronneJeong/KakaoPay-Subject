package com.kakaopay.memebership.repository;

import com.kakaopay.memebership.model.entity.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Integer> {

    Optional<BarcodeEntity> findByUserId(String userId);
}
