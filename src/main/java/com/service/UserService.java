package com.service;

import com.model.User;

/**
 * Интерфес для пользователя. Сервис-слой.
 * Сервис-класс для {@link User}
 *
 * @author Vlad Kazakov
 * @version 1.0
 */

public interface UserService {

    /**
     * Сохраняет пользователя
     * @param user
     */
    void save(User user);

    /**
     * Возвращает User по username
     * @param username
     * @return
     */
    User findByUsername(String username);
}
