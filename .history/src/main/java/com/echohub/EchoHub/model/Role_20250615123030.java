package com.echohub.EchoHub.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId; // простой тип

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Role(User user, RoleEnum role) {
        this.user = user;
        this.userId = user.getId();
        this.role = role;
    }
}