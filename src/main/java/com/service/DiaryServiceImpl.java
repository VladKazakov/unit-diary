package com.service;

import com.dao.DiaryDao;
import com.model.Diary;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс, реализующий интерфейс {@link DiaryService}
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public class DiaryServiceImpl implements DiaryService {

    private DiaryDao diaryDao;

    /**
     * Геттер
     * @return
     */
    public DiaryDao getDiaryDao() {
        return diaryDao;
    }

    /**
     * Сеттер
     * @param diaryDiaryDao
     */
    public void setDiaryDao(DiaryDao diaryDiaryDao) {
        this.diaryDao = diaryDiaryDao;
    }


    /**
     * Возвращает все записи заметок из БД
     * @return Возвращает все записи заметок из БД
     */
    @Transactional
    @Override
    public List getDiary() {
        return diaryDao.getDiary();
    }

    /**
     * Добавляет заметку
     * @param entity
     * @return true
     */
    @Transactional
    @Override
    public Boolean add(Diary entity) {
        diaryDao.add(entity);
        return true;
    }

    /**
     * Редактирует заметку
     * @param entity
     */
    @Transactional
    @Override
    public void update(Diary entity) {
        diaryDao.update(entity);
    }


    /**
     * Удаляет заметку
     * @param entity
     */
    @Transactional
    @Override
    public void delete(Diary entity) {
        diaryDao.delete(entity);
    }

}