package com.kakaopay.memebership.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "barcode")
})
@Entity
public class Barcode extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, length = 13)
    private String barcode;

    @Column(nullable = false, updatable = false, length = 16)
    private String ownerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barcode barcode1 = (Barcode) o;
        return barcode.equals(barcode1.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}
