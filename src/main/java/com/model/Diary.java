package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Бин-класс описывает объект заметки (Diary)
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
@Entity
@Table(name = "diary")
public class Diary implements Serializable {

    // Id заметки
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название заметки
    @Column(name = "name")
    private String name;

    // Текст заметки
    @Column(name = "text")
    private String text;

    // Дата создания в формате timestamp и запрещение обновления поля
    @Column(name = "created", updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    // Дата изменения в формате timestamp
    @Column(name = "updated")
    private Date updated;

    /**
     * Метод используется для создания даты и времени создания и изменения
     * заметки.
     */
    @PreUpdate
    @PrePersist
    public void updatedTimeStamps() {
        if (created == null) {
            created = new Date();
        }
        updated = new Date();
    }

    // Пустой конструктор
    public Diary() {
    }

    /**
     * Добавляем геттеры и сеттеры
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}