package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.rout.MostCommentedRouteDTO;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final RouteService routeService;

    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute("categories")
    public CategoryNames[] categories() {
        return CategoryNames.values();
    }

    @GetMapping("/")
    public String index (Model model){
        MostCommentedRouteDTO mostCommentedRoute = this.routeService.getMostCommentedRoute();

        model.addAttribute("mostCommentedRoute", mostCommentedRoute);

        return "index";
    }

    @GetMapping("/about")
    public String about (){
        return "about";
    }

}
