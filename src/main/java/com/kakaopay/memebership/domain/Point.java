package com.kakaopay.memebership.domain;

import com.kakaopay.memebership.domain.audit.AuditingFields;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Point extends AuditingFields {
    @Id
    private String barcode;
    @Id
    private String workType;

    private Number point;
}
