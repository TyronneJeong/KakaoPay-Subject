package com.kakaopay.memebership.user.model;

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
public class UserEntity extends AuditingFields {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, length = 16)
    private String userId;

    @Column(nullable = false, updatable = false, length = 36)
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return userId.equals(userEntity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
