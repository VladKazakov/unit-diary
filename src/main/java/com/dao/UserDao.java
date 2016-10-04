package com.dao;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для Пользователя - слой DAO {@link User}
 * JpaRepository — это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
 * Имя репозитория должно начинаться с имени сущности NameRepository (необязательно). UserDaoRepository
 * Наследуемся от {@JpaRepository}
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
