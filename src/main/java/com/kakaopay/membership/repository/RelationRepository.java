package com.kakaopay.membership.repository;

import com.kakaopay.membership.entity.Relation;
import com.kakaopay.membership.entity.RelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, RelationPK> {
}
