package ua.com.jon.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.service.AuthService;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("j_username") String login,
                        @RequestParam("j_password") String password) {
        final String emptyStr = "";
        //new HttpSessionRequestCache().getRequest()
        log.info("User login: " + login);
        log.info("User password: " + password);

        String forwardUrl = "/articlesList";
        AssemblaUser user = authService.getUser(password, login);
        if(user == null) {
            forwardUrl = "/j_spring_security_check?j_username=" + emptyStr + "&j_password=" + emptyStr;
        } else {
            forwardUrl = "/j_spring_security_check?j_username=" + user.getLogin() + "&j_password=" + user.getLogin();
        }
/*
        try {
            request.getRequestDispatcher(forwardUrl).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return "forward:" + forwardUrl;

    }
}
