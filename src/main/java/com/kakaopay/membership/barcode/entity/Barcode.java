package com.kakaopay.membership.barcode.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private @Column(length = 10) String barcode;
    private @Column Integer ownerId;

    @OneToMany(mappedBy = "userId")
    Set<BarcodeUser> barcodeUsers = new HashSet<>();

    public void addUser(BarcodeUser barcodeUser) {
        this.getBarcodeUsers().add(barcodeUser);
        barcodeUser.setRefBarcode(this);
    }

    public void removeUser(BarcodeUser barcodeUser) {
        this.getBarcodeUsers().remove(barcodeUser);
        barcodeUser.setRefBarcode(null);
    }
}
