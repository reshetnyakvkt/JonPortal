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
    @RequestMapping("/trainings/list.html")
    public String trainingsOracle(ModelMap modelMap)  {
        modelMap.put("item","mitem1");
        return "trainings/list";
    }

    @RequestMapping("/trainings/scheduling.html")
    public String trainingsSched(ModelMap modelMap)  {
        modelMap.put("item","mitem2");
        return "trainings/scheduling";
    }

    @RequestMapping("/trainings/reviews.html")
    public String trainingsReviews(ModelMap modelMap)  {
        modelMap.put("item","mitem5");
        return "trainings/reviews";
    }

    @RequestMapping("/trainings/rules.html")
    public String trainingsRules(ModelMap modelMap)  {
        modelMap.put("item","mitem3");
        return "trainings/rules";
    }

    @RequestMapping("/trainings/assistant.html")
    public String assistantRules(ModelMap modelMap)  {
        modelMap.put("item","mitem4");
        return "trainings/assistant";
    }
    @RequestMapping("/trainings/newcomer.html")
    public String newcomerRules(ModelMap modelMap)  {
        modelMap.put("item","mitem6");
        return "trainings/newcomer";
    }
}
