package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.dto.rout.AddRouteDTO;
import org.softuni.pathfinder.model.dto.rout.RoutDetailsDTO;
import org.softuni.pathfinder.model.dto.rout.RoutGetAllDTO;

import java.util.List;

public interface RouteService {
    void add(AddRouteDTO addRouteDTO);

    List<RoutGetAllDTO> getAllRoutes();

    RoutDetailsDTO findById(Long id);
}
