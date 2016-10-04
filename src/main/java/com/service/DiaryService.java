package com.service;

import com.model.Diary;

import java.util.List;

/**
 * Интерфес для заметки. Сервис-слой. {@link Diary}
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public interface DiaryService {

    /**
     * Метод используется для запроса в БД для возврата записей из БД.
     * @return записи из БД
     */
    List getDiary();

    /**
     * Добавление заметки
     * @param diary
     * @return значение true или false
     */
    Boolean add(Diary diary);

    /**
     * Обновление заметки
     * @param diary
     */
    void update(Diary diary);

    /**
     * Удаление заметки
     * @param diary
     */
    void delete(Diary diary);

}