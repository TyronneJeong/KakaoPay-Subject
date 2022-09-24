package com.kakaopay.memebership.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@ToString(callSuper = true)
@Entity
public class TranHistory extends AuditingFields {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, length = 24)
    private String tranHistId;

    @Setter @Column(nullable = false, updatable = false, length = 16)
    private String storeId;

    @Setter @Column(nullable = false, updatable = false, length = 13)
    private String barcode;

    @Setter @Column(nullable = false, updatable = false, length = 1)
    private String workTypeCd;

    @Setter @Column(nullable = false, updatable = false, length = 18)
    private BigDecimal beforePoint;

    @Setter @Column(nullable = false, updatable = false, length = 18)
    private BigDecimal tranPoint;

    @Setter @Column(nullable = false, updatable = false, length = 18)
    private BigDecimal afterPoint;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Setter @Column(nullable = false, updatable = false)
    private Instant tranDate;

    @Setter @Column(nullable = false, updatable = false,  length = 16)
    private String tranUserId;
}
