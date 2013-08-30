package ua.com.jon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/20/13
 */
@Controller
public class TrainingsController {
    @RequestMapping("/trainings/scheduling.html")
    public String trainingsSched(ModelMap modelMap)  {
        modelMap.put("item","item2");
        return "trainings/scheduling";
    }

    @RequestMapping("/trainings/reviews.html")
    public String trainingsReviews(ModelMap modelMap)  {
        modelMap.put("item","item2");
        return "trainings/reviews";
    }

    @RequestMapping("/trainings/rules.html")
    public String trainingsRules(ModelMap modelMap)  {
        modelMap.put("item","item2");
        return "trainings/rules";
    }
}
