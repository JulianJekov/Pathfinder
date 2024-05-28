package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.dto.rout.AddRouteDTO;
import org.softuni.pathfinder.model.dto.rout.RoutDetailsDTO;
import org.softuni.pathfinder.model.dto.rout.RoutGetAllDTO;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String regex = "(?<=v=)[\\w-]{11}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(addRouteDTO.getVideoUrl());

        if (matcher.find()) {
            String url = matcher.group(0);
            route.setVideoUrl(url);
        }
        this.routeRepository.save(route);
    }

    @Override
    public List<RoutGetAllDTO> getAllRoutes() {
        return this.routeRepository.findAll().stream()
                .map(route -> this.modelMapper.map(route, RoutGetAllDTO.class))
                .toList();
    }

    @Override
    public RoutDetailsDTO getDetails(Long id) {
        return this.modelMapper.map(this.routeRepository.findById(id), RoutDetailsDTO.class);
    }
}
