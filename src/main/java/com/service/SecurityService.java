package com.service;

/**
 * Интерфейс используется для поиска залогиненных пользователей. Сервис-слой.
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
public interface SecurityService {

    /**
     * Метод ищет залогиненных пользователей
     * @return залогиненных пользователей
     */
    String findLoggedInUsername();

    /**
     * Принимает имя пользователя и пароль
     * @param username
     * @param password
     */
    void autoLogin(String username, String password);
}
