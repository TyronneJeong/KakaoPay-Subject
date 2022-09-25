package com.kakaopay.memebership.history.domain;

import com.kakaopay.memebership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@ToString(callSuper = true)
@Entity
public class HistoryEntity extends AuditingFields {
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
