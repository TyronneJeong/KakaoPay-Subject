package com.kakaopay.membership.barcode.repository;

import com.kakaopay.membership.barcode.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, String> {

    Optional<Barcode> findByOwnerId(Integer userId);
}
