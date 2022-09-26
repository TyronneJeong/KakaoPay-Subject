package com.kakaopay.memebership.barcode.repository;

import com.kakaopay.memebership.barcode.repository.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Integer> {

    Optional<Barcode> findByOwnerId(String userId);
}
