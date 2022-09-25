package com.kakaopay.memebership.repository;

import com.kakaopay.memebership.model.entity.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository  extends JpaRepository<BarcodeEntity, String> {

}
