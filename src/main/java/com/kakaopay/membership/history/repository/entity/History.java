package com.kakaopay.membership.history.repository.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@ToString(callSuper = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class History extends AuditingFields {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, length = 24)
    private Integer tranHistId;

    @Setter @Column(nullable = false, updatable = false, length = 16)
    private Integer storeId;

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
    private Integer tranUserId;

}
