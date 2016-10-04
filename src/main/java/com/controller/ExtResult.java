package com.controller;

import com.model.Diary;

/**
 * Вспомогательный класс. Может быть использован для ответа клиенту, что сущность,
 * которую пытаемся записать в БД, дубликат или не дубликат
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public class ExtResult {
    private boolean success;
    private Diary data;

    /**
     * Конструктор класса ExtResult
     * @param success
     * @param data
     */
    public ExtResult(boolean success, Diary data) {
        this.success = success;
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Diary getData() {
        return data;
    }

    public void setData(Diary data) {
        this.data = data;
    }
}