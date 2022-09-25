package com.kakaopay.memebership.history.repository;

import com.kakaopay.memebership.barcode.repository.entity.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository  extends JpaRepository<BarcodeEntity, String> {

}
