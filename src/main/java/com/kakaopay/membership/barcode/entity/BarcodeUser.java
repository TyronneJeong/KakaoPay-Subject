package com.kakaopay.membership.barcode.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Table(name = "BarcodeUser")
@IdClass(BarcodeUserPK.class)
public class BarcodeUser implements Serializable {
    private @Id String barcode;
    private @Id Integer userId;
    private @Column String relationTypeCd;
    private @Transient @ManyToOne Barcode refBarcode;
}
