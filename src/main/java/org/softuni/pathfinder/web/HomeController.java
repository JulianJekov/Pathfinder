package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.enums.CategoryNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute("categories")
    public CategoryNames[] categories() {
        return CategoryNames.values();
    }

    @GetMapping("/")
    public String index (){
        return "index";
    }

    @GetMapping("/about")
    public String about (){
        return "about";
    }
}
