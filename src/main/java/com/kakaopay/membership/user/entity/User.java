package com.kakaopay.membership.user.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import com.kakaopay.membership.relation.entity.Relation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "\"User\"")
public class User extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 24, nullable = false)
    private String userName;

    @OneToMany(mappedBy = "userId")
    Set<Relation> relations = new HashSet<>();

    public void addRelation(Relation relation) {
        this.getRelations().add(relation);
        relation.setUser(this);
    }

    public void removeRelation(Relation relation) {
        this.getRelations().remove(relation);
        relation.setUser(null);
    }
}
