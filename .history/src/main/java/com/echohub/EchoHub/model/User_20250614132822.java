package com.echohub.EchoHub.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// Tell Hibernate that this is an entity
@Entity
// Table name in the database
@Table(name = "users")  
// Provides automatic generation of getters and setters for all class fields.
@Getter 
@Setter
// Generates a parameterless constructor to create instances of the class without passing arguments.
@NoArgsConstructor
// Generates a constructor with all parameters, allowing you to create instances of the class with the given values for all fields.
@AllArgsConstructor
// Generates a builder for the class
@Builder
public class User {
    @Id // Indicates that this field is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment when adding a new record
    private Long id;    // Unique user identifier

    @Column(nullable = false, unique = true)    // Username cannot be NULL and must be unique
    private String username;

    @Column(nullable = false, unique = true)    // Email cannot be NULL and must be unique
    private String email;

    @Column(nullable = false)   // Password cannot be NULL
    private String password;

    @OneToMany(
        mappedBy = "user",  // Indicates that the link is controlled by the “user” field in the Post entity.
        cascade = CascadeType.ALL,  // All operations (save, delete, etc.) will be cascaded to linked posts.
        fetch = FetchType.EAGER,    // Linked posts will be fetched immediately on user load.
        orphanRemoval = true    // If a post is no longer linked to a user, it will be automatically removed from the database.
        )
    @Builder.Default
     // If no value is provided in the builder, an empty HashSet will be used for roles
    private Set<UserRole> roles = new HashSet<>();  

    @Builder.Default
    // Default reputation value is set to 0 if not specified in the builder
    private int reputation = 0;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    // Registration date is set to the current time when the object is created
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    // Default status is set to ACTIVE if not provided in the builder
    private Status status = Status.ACTIVE;

    private String avatarUrl;    // URL of the user's avatar, can be null if not provided

    @PrePersist
    // This method is called before the entity is persisted in the database.
    // It ensures that registrationDate and status have default values if they are null.
    protected void onCreate() {
        if (registrationDate == null) {
            registrationDate = LocalDateTime.now();
        }
        if (status == null) {
            status = Status.ACTIVE;
        }
    }

    // Changes the user's role by clearing the existing roles and assigning a new one.
    public void changeRole(Role newRole) {
        roles.clear(); // Removes all existing roles
        roles.add(new UserRole(this, newRole)); // Assigns the new role to the user
    }

    // Updates the user's status.
    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

    // Updates the user's reputation by adding the specified value.
    public void updateReputation(int value) {
        this.reputation += value;
    }

}