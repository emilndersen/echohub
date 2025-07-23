package com.echohub.EchoHub.model;

// Ensure RoleEnum is in the same package or imported

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// This class represents a UserRole entity, which associates a user with a specific role.
@Entity // entity annotation indicates that this class is a JPA entity.
@Table(name = "user_roles") // Table name in the database
@IdClass(UserRoleId.class) // Using UserRoleId as the composite key
@Getter // Lombok annotation to generate getters for all fields
@Setter // Lombok annotation to generate setters for all fields
@NoArgsConstructor
// Lombok annotation to generate a no-args constructor

// This class represents a user role in the system, linking a user to a specific role.
public class UserRole implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId; // User identifier, part of the composite key

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public UserRole(User user, Role role) {
        this.user = user;
        this.userId = user.getId();
        this.role = role;
    }
}