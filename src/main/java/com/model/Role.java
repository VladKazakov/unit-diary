package com.model;


import javax.persistence.*;
import java.util.Set;

/**
 * Бин-класс описывает объект роли {@link User}
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
@Entity
@Table(name = "roles")
public class Role {


    // id роли Role. Генерируется базой данных
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Название роли
    @Column(name = "name")
    private String name;

    //
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * Пустой конструктор
     */
    public Role() {
    }

    /**
     * Добавляем геттеры, сеттеры
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Переределение метода toString
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

}
