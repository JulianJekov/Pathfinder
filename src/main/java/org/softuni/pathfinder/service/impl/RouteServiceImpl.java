package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.dto.AddRouteDTO;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AddRouteDTO addRouteDTO) {
        final Route route = this.modelMapper.map(addRouteDTO, Route.class);

        this.routeRepository.save(route);
    }
}
