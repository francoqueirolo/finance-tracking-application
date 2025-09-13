package com.codigo.monefy.users.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
@Getter
@Setter
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Evitar dejar por defecto el fetch en Eager porque vas a evitar el problema N+1
    // N+1 cuando usas Eager cada PasswordResetToken va a arrastrar un SELECT adicional para traer el User, aun cuando no lo uses.
    // Lazy carga perezosa, Eager carga ansiosa
    // Un usuario puede tener muchos tokens, pero un token solo pertenece a un usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String token;
    @Column(name = "expire_date")
    private LocalDateTime expireDate;
    private boolean isUsed;

    public PasswordResetToken() {}

    public PasswordResetToken(Integer id, User user, String token, LocalDateTime expireDate, boolean isUsed) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expireDate = expireDate;
        this.isUsed = isUsed;
    }
}
