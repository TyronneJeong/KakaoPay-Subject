package com.kakaopay.membership.entity;

import com.kakaopay.membership.entity.auditing.AuditingFields;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class History extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tranHistId;
    private Integer storeId;
    private @Column(length = 24) String storeName;
    private @Column(length = 10) String barcode;
    private @Column(length = 1) String workTypeCd;
    private @Column(length = 4) String inOutDvCd;
    private Integer tranPoint;
    private Integer totalPoint;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime approvedAt;
    private Integer tranUserId;
    private @Column(length = 24) String tranUserName;
}
