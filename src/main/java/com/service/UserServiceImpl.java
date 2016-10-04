package com.service;

import com.dao.RoleDao;
import com.dao.UserDao;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface.
 * Класс реализовывает интерфейс UserService
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired и вызываем соответствующие методы у интерфейса
     * {@link UserDao}
     */
    @Autowired
    private UserDao userDao;

    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired и вызываем соответствующие методы у интерфейса
     * {@link RoleDao}
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * Поле используется далее для вызова методов кодировки пароля
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Сохраняем пользователя
     * @param user
     */
    @Override
    public void save(User user) {
        // Получаем у пользователя пароль и кодируем с помощью @bCryptPasswordEncoder
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // Присваиваем пользователю роль
        Set<Role> roles = new HashSet<>();
        // Присваиваем пользователю роль по умолчанию 1L (user)
        roles.add(roleDao.getOne(1L));
        // устанавливаем роль
        user.setRoles(roles);
        // сохраняем пользователя
        userDao.save(user);
    }

    /**
     * Получаем пользователя из базы данных по его имени
     * @param username
     * @return пользователя по его имени
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
