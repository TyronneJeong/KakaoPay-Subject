package com.kakaopay.membership.repository;

import com.kakaopay.membership.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, String> {
    Optional<Barcode> findByOwnerId(Integer userId);
}
