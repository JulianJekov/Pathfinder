package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.rout.AddRouteDTO;
import org.softuni.pathfinder.model.dto.rout.RoutDetailsDTO;
import org.softuni.pathfinder.model.dto.rout.RoutGetAllDTO;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;
import org.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    private final RouteService routeService;

    @Autowired
    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryNames.values());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRoute(AddRouteDTO addRouteDTO){

        this.routeService.add(addRouteDTO);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("all")
    public ModelAndView allRoutes() {
        ModelAndView modelAndView = new ModelAndView("routes");
        List<RoutGetAllDTO> routes = this.routeService.getAllRoutes();
        modelAndView.addObject("routes", routes);
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView learnMore(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("route-details");
        RoutDetailsDTO routDetails = this.routeService.findById(id);
        modelAndView.addObject("details", routDetails);
        return modelAndView;
    }
}
