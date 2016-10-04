package com.dao;

import com.model.Diary;

import java.util.List;

/**
 * Интерфейс для Заметки - слой DAO {@link Diary}
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public interface DiaryDao {

    /**
     * Метод для отображения данных БД в клиентской части. Используется в GET-запросе
     */
    List getDiary();

    /**
     * Метод используется для добавления в БД заметк {@link Diary}. Используется в POST-запросе
     * @param entity
     */
    void add(Diary entity);

    /**
     * Метод используется для изменения в БД заметк {@link Diary}. Используется в PUT-запросе
     * @param entity
     */
    void update(Diary entity);

    /**
     * Метод используется для изменения в БД заметк {@link Diary}. Используется в DELETE-запросе
     * @param entity
     */
    void delete(Diary entity);

}