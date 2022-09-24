package com.kakaopay.memebership.domain;

import com.kakaopay.memebership.domain.audit.AuditingFields;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends AuditingFields {

    @Id
    @GeneratedValue
    private String userId;

    private String userName;

}
