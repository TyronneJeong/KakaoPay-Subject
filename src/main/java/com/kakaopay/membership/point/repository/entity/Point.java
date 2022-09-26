package com.kakaopay.membership.point.repository.entity;

import com.kakaopay.membership.barcode.entity.BarcodeUserPK;
import com.kakaopay.membership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@IdClass(PointPK.class)
public class Point extends AuditingFields {
    @Id
    private String barcode;
    @Id
    private String workTypeCd;

    private Integer storeId;

    @Setter
    @Column
    private Integer point;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return barcode.equals(point.barcode) && workTypeCd.equals(point.workTypeCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, workTypeCd);
    }
}
