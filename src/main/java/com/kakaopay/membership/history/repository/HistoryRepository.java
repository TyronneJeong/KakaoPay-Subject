package com.kakaopay.membership.history.repository;

import com.kakaopay.membership.history.repository.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository  extends JpaRepository<History, String> {

}
