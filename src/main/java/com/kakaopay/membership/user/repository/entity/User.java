package com.kakaopay.membership.user.repository.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name ="\"User\"")
public class User extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;

    @Column(length = 24, nullable = false)
    private String userName;
}
