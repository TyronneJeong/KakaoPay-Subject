package com.kakaopay.membership.entity;

import com.kakaopay.membership.entity.auditing.AuditingFields;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Table(name = "Relation")
@IdClass(RelationPK.class)
public class Relation extends AuditingFields implements Serializable {
    private @Id Integer userId;
    private @Id Integer friendId;
    private @Column String relationTypeCd;
    private @Transient @ManyToOne User user;
}