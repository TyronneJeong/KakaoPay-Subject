package com.kakaopay.memebership.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class BarcodeUser {

    @Id
    @Column(nullable = false, updatable = false, length = 13)
    private String barcode;

    @Id
    @Column(nullable = false, updatable = false, length = 16)
    private String userId;

    @Column(nullable = false, updatable = false, length = 1)
    private String relationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarcodeUser that = (BarcodeUser) o;
        return barcode.equals(that.barcode) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, userId);
    }
}
