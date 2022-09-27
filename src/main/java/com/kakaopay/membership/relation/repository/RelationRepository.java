package com.kakaopay.membership.relation.repository;

import com.kakaopay.membership.relation.entity.Relation;
import com.kakaopay.membership.relation.entity.RelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, RelationPK> {
}
