package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.dto.rout.AddRouteDTO;
import org.softuni.pathfinder.model.dto.rout.RoutDetailsDTO;
import org.softuni.pathfinder.model.dto.rout.RoutGetAllDTO;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.service.RouteService;
import org.softuni.pathfinder.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private static final String BASE_GPX_COORDINATES_PATH = "src\\main\\resources\\gpx\\";

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void add(AddRouteDTO addRouteDTO) {
        final Route route = this.modelMapper.map(addRouteDTO, Route.class);

        String filePath = getFilePath(route.getName());
        boolean isUploaded = uploadGpxCoordinates(addRouteDTO, filePath);

        if (isUploaded) {
            route.setGpxCoordinates(filePath);
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

    private String getFilePath(String routName) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                loggedUser.getUsername(),
                routNameTransform(routName),
                UUID.randomUUID());
    }

    private boolean uploadGpxCoordinates(AddRouteDTO addRouteDTO, String filePath) {
        try {
            File file = new File(BASE_GPX_COORDINATES_PATH + filePath);
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(addRouteDTO.getGpxCoordinates().getBytes());
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private String routNameTransform(String routName) {
        return routName.toLowerCase().replaceAll("\\s+", "_");
    }
}
