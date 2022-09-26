package com.kakaopay.membership.store.repository.entity;

import com.kakaopay.membership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Store extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    @Column(length = 24, nullable = false)
    private String storeName;

    @Column(length = 1)
    private String workTypeCd;
}
