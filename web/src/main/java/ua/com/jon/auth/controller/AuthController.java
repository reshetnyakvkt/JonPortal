package ua.com.jon.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.jon.auth.service.AuthService;
import ua.com.jon.common.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/29/13
 */
@Controller
public class AuthController {
    private static Logger log = Logger.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String showArticlesList(Model model
                                   /*,@RequestParam("SPRING_SECURITY_LAST_EXCEPTION") String exception*/) {
        model.addAttribute("error", "true");
//        model.addAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);

        return "index";
    }

/*    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("j_username") String login,
                        @RequestParam("j_password") String password) {
        final String emptyStr = "";
        log.info("User login: " + login);
        log.info("User password: " + password);

        String forwardUrl = "/articlesList";
        AssemblaUser user = authService.getAssemblaUser(password, login);
        if (user == null) {
            forwardUrl = "/j_spring_security_check?j_username=" + emptyStr + "&j_password=" + emptyStr;
        } else {
            forwardUrl = "/j_spring_security_check?j_username=" + user.getLogin() + "&j_password=" + user.getLogin();
        }

        return "forward:" + forwardUrl;

    }*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("j_username") String login,
                        @RequestParam("j_password") String password) {
        final String emptyStr = "";
        log.info("User login: " + login);
        log.info("User password: " + password);

        String forwardUrl = "/articlesList";
        User user = authService.getDBUser(login, password);
        if (user == null) {
            forwardUrl = "/j_spring_security_check?j_username=" + emptyStr + "&j_password=" + emptyStr;
        } else {
            forwardUrl = "/j_spring_security_check?j_username=" + user.getLogin() + "&j_password=" + user.getLogin();
        }

        return "forward:" + forwardUrl;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam("j_username") String login,
                           @RequestParam("j_password") String password) {

        log.info("New User login: " + login);
        log.info("New User password: " + password);

        String forwardUrl;
        if (password == null || password.equals("")) {
            model.addAttribute("message", "Пароль не может быть пустым.");
            return "/register";
        }
        if (login == null || login.equals("")) {
            model.addAttribute("message", "Логин не может быть пустым.");
            return "/register";
        }
        try {
            User user = authService.getUserFromDBByName(login);
            if (user == null) {
                authService.createNewUser(login, password);
            } else if (user.getPassword() == null || user.getPassword().equals("")) {
                user.setPassword(password);
                authService.updateUser(user);
            }
            if (password.equals(user.getPassword())) {
                forwardUrl = "/j_spring_security_check?j_username=" + login + "&j_password=" + login;
                return "forward:" + forwardUrl;
            } else {
                model.addAttribute("message", "Пользователь с таким именем уже существует.");
            }
            return "/register";

        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Логин неверный");
            return "/register";
        }
    }
}
