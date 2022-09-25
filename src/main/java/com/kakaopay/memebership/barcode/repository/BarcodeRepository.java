package com.kakaopay.memebership.barcode.repository;

import com.kakaopay.memebership.barcode.repository.entity.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Integer> {

    Optional<BarcodeEntity> findByOwnerId(String userId);
}
