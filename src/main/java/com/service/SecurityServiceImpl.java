package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Класс, реализующий интерфейс {@link SecurityService}
 * @author Vlad Kazakov
 * @version 1.0
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    // добавляем логгер
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    // менеджер аутентификации
    @Autowired
    private AuthenticationManager authenticationManager;

    // Внедряем зависимость с помощью spring аннотации @Autowired и вызываем соответствующие методы у интерфейса
    @Autowired
    private UserDetailsService userDetailsService; //

    /**
     * @return Получаем залогиненного пользователя
     */
    @Override
    public String findLoggedInUsername() {
        // Создаем объект, который содержит информацию по пользователю
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        // Если userDetails является типом UserDetails, то возвращаем имя пользователя
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    /**
     * Информация об авторизованных пользователях
     * @param username
     * @param password
     */
    @Override
    public void autoLogin(String username, String password) {
        // Создаем экземпляр UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

            // если пользователь авторизован, то добавляем в лог информацию об авторизованном пользователе
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug(String.format("Successfully %s auto logged in", username));
        }
    }
}
