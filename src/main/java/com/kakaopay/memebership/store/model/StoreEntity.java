package com.kakaopay.memebership.store.model;

import com.kakaopay.memebership.global.model.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class StoreEntity extends AuditingFields {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, length = 16)
    private String storeId;

    @Column(nullable = false, updatable = true, length = 72)
    private String storeName;

    @Column(length = 1)
    private String workTypeCd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity storeEntity = (StoreEntity) o;
        return storeId.equals(storeEntity.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId);
    }
}
