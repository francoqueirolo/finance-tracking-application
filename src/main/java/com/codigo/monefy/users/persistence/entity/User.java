package com.codigo.monefy.users.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        }
)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;

    @Column(name = "password")
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String phone;
    private String profileImageUrl;
    private boolean isActive;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(Integer id, String username, String email, String passwordHash, String firstName, String lastName, String phone, String profileImageUrl, boolean isActive, boolean isVerified, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
        this.isActive = isActive;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive && isVerified == user.isVerified && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && Objects.equals(profileImageUrl, user.profileImageUrl) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, passwordHash, firstName, lastName, phone, profileImageUrl, isActive, isVerified, createdAt, updatedAt, roles);
    }
}
