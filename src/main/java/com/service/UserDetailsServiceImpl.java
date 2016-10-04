package com.service;

import com.dao.UserDao;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация интерфейса из фреймворка {@link UserDetailsService}
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired и вызываем соответствующие методы у интерфейса
     * {@link UserDao}
     */
    @Autowired
    private UserDao userDao;

    /**
     * Формируется информация о пользователе (Роль) {@link UserDao}
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Создаем экземпляр пользователя, которого мы ищем в БД по username
        User user = userDao.findByUsername(username);

        // Создаем множестао разрешений
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        // Получем все роли, которые есть и добавляем из в разрещения grantedAuthorities
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
