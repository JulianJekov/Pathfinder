package org.softuni.pathfinder.web.rest;

import org.softuni.pathfinder.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteRestController {

    private final RouteService routeService;

    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("routes/coordinates/{id}")
    public List<List<Double>> getRouteCoordinates(@PathVariable("id") Long routeId) {
        return routeService.getCoordinates(routeId);
    }
}
