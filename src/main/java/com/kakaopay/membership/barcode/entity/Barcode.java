package com.kakaopay.membership.barcode.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
}
