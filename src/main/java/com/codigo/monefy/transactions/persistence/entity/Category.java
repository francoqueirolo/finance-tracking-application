package com.codigo.monefy.transactions.persistence.entity;

import com.codigo.monefy.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String description;
    private String color;
    private String icon;

    public Category() {}

    public Category(Long id, User user, String name, String description, String color, String icon) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.color = color;
        this.icon = icon;
    }
}
