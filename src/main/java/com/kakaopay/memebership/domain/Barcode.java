package com.kakaopay.memebership.domain;

import com.kakaopay.memebership.domain.audit.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private long barcode;

    private long ownerId;



}
