package com.model;
import javax.persistence.*;
import java.util.Set;


/**
 * Бин-класс описывает объект пользователя {@link Role}
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User {

    // Id пользователя User. Генерируется базой данных
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Логин
    @Column(name = "username")
    private String username;

    // Пароль
    @Column(name = "password")
    private String password;

    // Поле для проверки пароля
    @Transient
    private String confirmPassword;

    /* Сопоставление пользователей и их ролей во множестве.Связь "многие ко многим" */
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
                            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * Пустой конструктор
     */
    public User(){}

    /**
     * Добавляем геттеры, сеттеры
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
