package com.kakaopay.membership.store.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Store extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;
    private @Column(length = 24, nullable = false) String storeName;
    private @Column(length = 1) String workTypeCd;
}
