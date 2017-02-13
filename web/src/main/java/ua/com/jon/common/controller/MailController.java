package ua.com.jon.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.jon.common.service.MailService;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.06.16
 */
@Controller
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public String getTask(@RequestParam("courseType") String courseType,
                          @RequestParam("name") String name,
                          @RequestParam("phone") String phone,
                          @RequestParam("email") String email,
                          @RequestParam("message") String message,
                          ModelMap model) {
        mailService.sendEmail("new student message", "Course: " + courseType + "\n" +
            "Name: " + name + "\n" +
            "Phone: " + phone + "\n" +
            "Mail: " + email + "\n" +
            "Message:" + message);
        return "landing/index";
    }
}
