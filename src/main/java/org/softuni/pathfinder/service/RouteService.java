package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.dto.rout.*;
import org.softuni.pathfinder.model.enums.CategoryNames;

import java.util.List;

public interface RouteService {
    void add(AddRouteDTO addRouteDTO);

    List<RoutGetAllDTO> getAllRoutes();

    RouteDetailsDTO getDetails(Long id);

    void uploadPicture(UploadPictureRouteDTO uploadPictureRouteDTO);

    List<RouteCategoryDTO> findAllByCategoryName(CategoryNames categoryNames);

    List<List<Double>> getCoordinates(Long routeId);
}
