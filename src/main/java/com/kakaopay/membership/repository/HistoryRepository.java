package com.kakaopay.membership.repository;

import com.kakaopay.membership.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {
    List<History> findAllByBarcodeAndApprovedAtBetweenOrderByApprovedAtDesc(String barcode, LocalDateTime fromDate, LocalDateTime toDate);
}
