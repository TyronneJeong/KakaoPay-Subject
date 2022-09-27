package com.kakaopay.membership.history.repository;

import com.kakaopay.membership.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {
    List<History> findAllByBarcodeCreatedAtBetween(String barcode, Timestamp fromDate, Timestamp toDate);
}
