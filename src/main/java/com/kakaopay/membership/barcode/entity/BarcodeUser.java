package com.kakaopay.membership.barcode.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "BarcodeUser")
@IdClass(BarcodeUserPK.class)
public class BarcodeUser implements Serializable {
    @Id
    private String barcode;
    @Id
    private Integer userId;
    private String relationTypeCd;

}
