package com.kakaopay.membership.barcode.repository;

import com.kakaopay.membership.barcode.entity.Barcode;
import com.kakaopay.membership.barcode.entity.BarcodeUserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcodeUserRepository extends JpaRepository<Barcode, BarcodeUserPK> {
}
