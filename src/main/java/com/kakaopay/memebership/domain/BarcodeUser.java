package com.kakaopay.memebership.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BarcodeUser {
    @Id
    private String barcode;

    @Id
    private String userId;
    private String relationType;
}
