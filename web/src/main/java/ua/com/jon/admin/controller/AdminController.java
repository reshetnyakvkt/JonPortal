package ua.com.jon.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 20.04.13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class AdminController {

    @RequestMapping(value = "/admin/index.html", method = RequestMethod.GET)
    public String indexGWT(Model model) {
        return "admin/index";
    }
}
