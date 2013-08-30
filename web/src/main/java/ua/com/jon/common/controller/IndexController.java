package ua.com.jon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String mainPage(ModelMap modelMap)  {
        modelMap.put("item","item1");
        return "index";
    }

    @RequestMapping("/trainings/index.html")
    public String trainingsIndex(ModelMap modelMap)  {
        modelMap.put("item","item2");
        return "trainings/list";
    }

    @RequestMapping("/lessons.html")
    public String lessons(ModelMap modelMap)  {
        modelMap.put("item","item3");
        return "lessons";
    }

    @RequestMapping("/tasks.html")
    public String tasks(ModelMap modelMap)  {
        modelMap.put("item","item4");
        return "tasks";
    }


    @RequestMapping("/tasksg.html")
    public String examinator(ModelMap modelMap)  {
        modelMap.put("item","item6");
        return "tasksg";
    }

//    @RequestMapping("/tasks.html")
//    public String download(ModelMap modelMap)  {
//        modelMap.put("item","item5");
//        return "download";
//    }
}
