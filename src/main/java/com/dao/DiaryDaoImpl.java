package com.dao;


import com.model.Diary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Класс, реализующий интерфейс {@link DiaryDao}
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public class DiaryDaoImpl implements DiaryDao {

    @PersistenceContext
    private EntityManager emf;

    /**
     * Метод реализует запрос в БД
     * @return все записи из БД для отображдения в клиентской части
     */
    @Override
    public List getDiary() {
            return emf.createQuery("select c from Diary c").getResultList();
        }

    /**
     * Метод реализует запрос в БД. Добавляет запись (заметку) в БД
     * @param entity
     */
    @Override
    public void add(Diary entity) {
        emf.persist(entity);
    }

    /**
     * Метод реализует запрос в БД. Изменяет запись (заметку) в БД
     * @param entity
     */
    @Override
    public void update(Diary entity) {
        emf.merge(entity);
    }

    /**
     * Метод реализует запрос в БД. Удаляет запись (заметку) в БД
     * @param entity
     */
    @Override
    public void delete(Diary entity) {
        emf.remove(emf.getReference(Diary.class, entity.getId()));
    }
}