package com.kakaopay.membership.entity;

import com.kakaopay.membership.entity.auditing.AuditingFields;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Barcode")
public class Barcode extends AuditingFields implements Serializable {
    @Id
//    @GeneratedValue(generator = "barcode")
//    @GenericGenerator(name = "barcode", strategy = "com.kakaopay.membership.entity.generator.BarcodeGenerator")
    private @Column(length = 10) String barcode;
    private @Column Integer ownerId;
}
