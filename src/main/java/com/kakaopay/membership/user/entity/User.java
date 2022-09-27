package com.kakaopay.membership.user.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import com.kakaopay.membership.relation.entity.Relation;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "\"User\"")
public class User extends AuditingFields implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 24, nullable = false)
    private String userName;
}
