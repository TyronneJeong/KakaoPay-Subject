package com.kakaopay.membership.history.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @Column
    private Integer tranHistId;

    @Setter @Column
    private Integer storeId;

    @Setter @Column(nullable = false, updatable = false,  length = 24)
    private String storeName;

    @Setter @Column(nullable = false, updatable = false, length = 10)
    private String barcode;

    @Setter @Column(nullable = false, updatable = false, length = 1)
    private String workTypeCd;

    @Setter @Column(nullable = false, updatable = false, length = 4)
    private String inOutDvCd;

    @Setter @Column(nullable = false, updatable = false)
    private Integer tranPoint;

    @Setter @Column(nullable = false, updatable = false)
    private Integer totalPoint;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Setter @Column(nullable = false, updatable = false)
    private Timestamp tranDate;

    @Setter @Column(nullable = false, updatable = false,  length = 16)
    private Integer tranUserId;

    @Setter @Column(nullable = false, updatable = false,  length = 24)
    private String tranUserName;

}
