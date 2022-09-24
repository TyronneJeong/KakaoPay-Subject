package com.kakaopay.memebership.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
public class Point extends AuditingFields {
    @Id
    private String barcode;
    @Id
    private String workTypeCd;

    @Setter
    @Column(length = 18)
    private Number point;

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
