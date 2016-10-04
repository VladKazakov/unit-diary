package com.dao;

import com.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для Роли - слой DAO {@link Role}
 * JpaRepository — это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
 * Имя репозитория должно начинаться с имени сущности NameRepository (необязательно). RoleDaoRepository
 * Наследуемся от {@JpaRepository}
 *
 * @autor Vlad Kazakov
 * @version 1.0
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}
