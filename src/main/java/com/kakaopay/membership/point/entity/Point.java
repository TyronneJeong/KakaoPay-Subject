package com.kakaopay.membership.point.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    private @Id String barcode;
    private @Id String workTypeCd;
    private @Column Integer point;
}
