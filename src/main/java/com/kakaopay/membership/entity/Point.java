package com.kakaopay.membership.entity;

import com.kakaopay.membership.entity.auditing.AuditingFields;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Entity
@IdClass(PointPK.class)
public class Point extends AuditingFields implements Serializable {
    private @Id @Column(length = 10) String barcode;
    private @Id @Column(length = 1) String workTypeCd;
    private @Column Integer point;
}
