package com.validator;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Валидатор для {@link User} класса,
 * Имплементируется от {@link Validator} интерфейса.
 * Используется для контроля правильности при регистрации
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
@Component
public class UserValidator implements Validator {

    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired
     * и вызываем соответствующие методы у сервиса
     * {@link UserService}
     */
    @Autowired
    private UserService userService;

    /**
     * Метод должен быть {@link Validator}
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Используется для определеня ошибок при вводе
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        // Если дина username < 8 или > 32, то вызываем сообщение об ошибке
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        // Если такой пользователь есть в БД, то сообщаем об этом
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        // Если дина пароля < 8 или > 32, то вызываем сообщение об ошибке
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        // Проверяем, совпадают ли пароли
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
