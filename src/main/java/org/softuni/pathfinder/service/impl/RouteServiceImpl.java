package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.dto.AddRouteDTO;
import org.softuni.pathfinder.model.entity.Category;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.CategoryRepository;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.service.RouteService;
import org.softuni.pathfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, UserService userService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public void add(AddRouteDTO addRouteDTO) {
        this.modelMapper.typeMap(AddRouteDTO.class, Route.class).addMappings(m -> {
            m.skip(Route::setCategories);
        });
        final Route route = this.modelMapper.map(addRouteDTO, Route.class);

        Set<Category> categories = categoryRepository.findByNameIn(addRouteDTO.getCategories());
        route.setCategories(categories);

        final User author = this.userService.getLoggedUser();
        route.setAuthor(author);

        this.routeRepository.save(route);
    }
}
