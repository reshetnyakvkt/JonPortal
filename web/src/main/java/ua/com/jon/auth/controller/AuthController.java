package ua.com.jon.auth.controller;

import com.jon.tron.domain.GitUser;
import com.jon.tron.service.processor.Crypt;
import com.jon.tron.service.vc.git.GitblitClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.jon.auth.service.AuthService;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.service.MailService;
import ua.com.jon.common.service.RegisterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Autowired
    private RegisterService registerService;

    @Autowired
    private GitblitClient gitblitClient;

    @Autowired
    private MailService mailService;

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
            forwardUrl = "/j_spring_security_check?j_username=" + user.getLogin() + "&j_password=" + user.getPassword();
        }

        return "forward:" + forwardUrl;

    }

    @RequestMapping(value = "/activation", method = RequestMethod.GET)
    public String activation(Model model,
                             @RequestParam("user") String login,
                             @RequestParam("code") String activationCode) {
        if (login == null || login.isEmpty()) {
            return gotoRegisterWithError(model, "Имя пользователя не может быть пустым");
        }
        if (activationCode == null || activationCode.isEmpty()) {
            return gotoRegisterWithError(model, "Код не может быть пустым");
        }

        User user = authService.getUserFromDBByName(login);

        if (user != null && activationCode.equals(user.getActivationCode())) {
            authService.activateUser(user);
        } else {
            return gotoRegisterWithError(model, "Неверные параметры активации");
        }

        String forwardUrl = "/j_spring_security_check?j_username=" + user.getLogin() + "&j_password=" + user.getPassword();
        return "forward:" + forwardUrl;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam("j_username") String login,
                           @RequestParam("j_password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("group") String groupIdStr,
                           @RequestParam("code") String code) {

        log.info("New User login: " + login);
        log.info("New User password: " + password);

        String forwardUrl;
        if (password == null || password.equals("")) {
            return gotoRegisterWithError(model, "Пароль не может быть пустым.");
        }
        if (code == null || code.equals("")) {
            return gotoRegisterWithError(model, "Код не может быть пустым.");
        }
        if (login == null || login.equals("")) {
            return gotoRegisterWithError(model, "Логин не может быть пустым.");
        }
        if (!login.matches("^[a-z]{1,30}_[a-z]{1,30}$")) {
            return gotoRegisterWithError(model, "Логин должен быть в формате \"имя_фамилия\", латинскими маленькими буквами");
        }
        if (!password.matches("^[a-zA-Z0-9]{1,30}$")) {
            return gotoRegisterWithError(model, "Пароль может содержать только буквы и цифры");
        }
        Long groupId;
        try {
            groupId = Long.parseLong(groupIdStr);
        } catch (Exception nfe) {
            model.addAttribute("message", "Выбрана неверная группа");
            return "/register";
        }

        List<GroupDTO> activeGroups = authService.getGroupsById(groupId);
        if (!activeGroups.isEmpty() && !activeGroups.get(0).getCode().equals(code)) {
            model.addAttribute("message", "Неверный код группы");
            return "/register";
        }
        User user = authService.getUserFromDBByName(login);

        if (user != null && !authService.isUserInGroup(user, groupId)) {
            authService.addUserToGroup(user, activeGroups.get(0).getId());
            forwardUrl = "/j_spring_security_check?j_username=" + login + "&j_password=" + password;
            return "forward:" + forwardUrl;
        } else if (user != null) {
            model.addAttribute("message", "Пользователь с таким логином уже сущестует");
            return "/register";
        }

        if (!mailService.isEmailValid(email)) {
            model.addAttribute("message", "Не верный email");
            return "/register";
        }

        if (authService.isUserWithMailExists(email)) {
            model.addAttribute("message", "Пользователь с таким email уже существует");
            return "/register";
        }

        try {
            if (activeGroups.isEmpty()) {
                model.addAttribute("message", "Нет такой группы");
                return "/register";
            }

            if (!activeGroups.get(0).getCode().equals(code)) {
                model.addAttribute("message", "Неверный код");
                return "/register";
            }

            String sha1 = Crypt.sha1(login + password + email + String.valueOf(System.currentTimeMillis()));
            sha1 = sha1.substring(0, 35);

            authService.createNewUser(login, password, email, sha1, activeGroups);
            ResponseEntity<GitUser> entity = gitblitClient.createUser(login, password);
            log.info("Создание пользователя gitblit: " + entity);

            mailService.sendEmail(email, "Jon registration", "For complete registration, please follow by this link " +
                    "http://www.jon.com.ua/activation?user=" + user.getLogin() + '&' + "code=" + sha1);
            mailService.sendEmail("new user registred", "User registred: " + login);

            forwardUrl = "/j_spring_security_check?j_username=" + login + "&j_password=" + password;
            return "forward:" + forwardUrl;

        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Логин неверный");
            return "/register";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "/register";
        }
    }

    private String gotoRegisterWithError(Model model, String message) {
        List<GroupDTO> activeGroups = registerService.getActiveGroups();
        model.addAttribute("groups", activeGroups);
        model.addAttribute("message", message);
        return "/register";
    }
}
