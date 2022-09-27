package com.kakaopay.membership.history.repository;

import com.kakaopay.membership.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {
    List<History> findAllByBarcodeAndCreatedAtBetween(String barcode, LocalDateTime fromDate, LocalDateTime toDate);
}
