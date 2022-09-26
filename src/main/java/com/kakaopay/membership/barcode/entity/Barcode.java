package com.kakaopay.membership.barcode.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Barcode")
public class Barcode extends AuditingFields {

    @Id
    @GeneratedValue(generator = "barcode")
    @GenericGenerator(name = "barcode", strategy = "com.kakaopay.membership.barcode.entity.BarcodeGenerator")
    @Column(length = 10)
    private String barcode;

    @Column(length = 9)
    private BigInteger ownerId;

    // 유저와 1:N 관계
//    @OneToMany(mappedBy = "userId")
//    Set<User> users = new HashSet<>();

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
