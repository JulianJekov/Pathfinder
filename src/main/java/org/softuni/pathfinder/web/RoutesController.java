package org.softuni.pathfinder.web;

import jakarta.validation.Valid;
import org.softuni.pathfinder.model.dto.rout.AddRouteDTO;
import org.softuni.pathfinder.model.dto.rout.RoutDetailsDTO;
import org.softuni.pathfinder.model.dto.rout.RoutGetAllDTO;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;
import org.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    private final RouteService routeService;

    @Autowired
    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @ModelAttribute("categories")
    public CategoryNames[] categories() {
        return CategoryNames.values();
    }

    @ModelAttribute("addRouteDTO")
    public AddRouteDTO addRouteDTO() {
        return new AddRouteDTO();
    }


    @GetMapping("/add")
    public ModelAndView addRoute() {
        return new ModelAndView("add-route");
    }

    @PostMapping("/add")
    public ModelAndView addRoute(@Valid AddRouteDTO addRouteDTO, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addRouteDTO", addRouteDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addRouteDTO", bindingResult);
        return new ModelAndView("redirect:/routes/add");
        }

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
        RoutDetailsDTO routDetails = this.routeService.getDetails(id);
        modelAndView.addObject("details", routDetails);
        return modelAndView;
    }
}
