package com.kakaopay.membership.user.repository.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name ="\"User\"")
public class User extends AuditingFields {

    @Id
    @GeneratedValue
    @Column(length = 9)
    private long userId;

    @Column(nullable = false, updatable = false, length = 24)
    private String userName;

}
