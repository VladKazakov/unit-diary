package com.controller;

import com.model.User;
import com.service.SecurityService;
import com.service.UserService;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер для {@link User}.
 *
 * @author Влад Казаков
 * @version 1.0
 */
@Controller
public class UserController {
    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired и
     * вызываем соответствующие методы у сервиса {@link UserService}
     */
    @Autowired
    private UserService userService;

    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired и
     * вызываем соответствующие методы у сервиса {@link SecurityService}
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Внедряем зависимость с помощью spring аннотации @Autowired и
     * вызываем соответствующие методы у {@link UserValidator}
     */
    @Autowired
    private UserValidator userValidator;



    /**
     * Создаем страниицы
     * @param model
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        // создаем нового юзера из формы на странице
        model.addAttribute("userForm", new User());
        return "/registration";
    }

    /**
     * Передает данные формы со страницы регистрации
     * @param userForm
     * @param bindingResult
     * @param model
     * @return если ошиблись, то пользователя оставляем на стрнице регистрции, иначе, /welcome
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);
        // если ошибка при вводе, которой нам сообщи валидатор, то пользователя возвращаем обратно на страницу registration
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        // если ошибок нет, то сохраняем данные
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
            return "redirect:/welcome";
    }

    /**
     * Страница аутентификации пользователя.
     * @param model
     * @param error
     * @param logout
     * @return либо сообщение об ошибке ввода, либо, если вышли из приложения сообщение ог выходе и
     * предложении зарегистрироваться вновь
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        // если есть ошибки, то сообщаем об этом (имя пользователя или пароль)
        if (error != null) {
            model.addAttribute("error", "Логин и пароль некорректны");
        }

        // если логаут, то выводится сообщение
        if (logout != null) {
            model.addAttribute("message", "Вы вышли");
        }
        return "login";
    }

    /**
     * Метод для завершении сессии.
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * Главная страница приложения
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "main";
    }

}
