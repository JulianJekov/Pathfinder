package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.exceptions.RouteNotFoundException;
import org.softuni.pathfinder.exceptions.UserNotFoundException;
import org.softuni.pathfinder.model.dto.rout.*;
import org.softuni.pathfinder.model.entity.Picture;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.repository.PictureRepository;
import org.softuni.pathfinder.repository.RouteRepository;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.service.RouteService;
import org.softuni.pathfinder.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    private static final String BASE_GPX_COORDINATES_PATH = "src\\main\\resources\\gpx\\";
    private static final String BASE_ROUTE_PICTURES_PATH = "src\\main\\resources\\static\\images\\";
    private static final String IMAGE_PRE_PATH_FOR_THYMELEAF = "\\images\\";
    private static final String GALLERY = "gallery";

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserRepository userRepository, PictureRepository pictureRepository) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
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
    public RouteDetailsDTO getDetails(Long id) {
        return this.modelMapper.map(this.routeRepository.findById(id)
                        .orElseThrow(() -> new RouteNotFoundException("Route with id " + id + " not found!")),
                RouteDetailsDTO.class);
    }

    @Override
    public void uploadPicture(UploadPictureRouteDTO uploadPictureRouteDTO) {
        MultipartFile pictureFile = uploadPictureRouteDTO.getPicture();

        boolean isPrimary = uploadPictureRouteDTO.getPrimary();

        Long routId = uploadPictureRouteDTO.getId();

        Route route = this.routeRepository.findById(routId)
                .orElseThrow(() -> new RouteNotFoundException
                        ("Route with id " + routId + " not found!"));

        String picturePath = getPicturePath(pictureFile, route.getName(), isPrimary);

        try {
            File file = new File(BASE_ROUTE_PICTURES_PATH + picturePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pictureFile.getBytes());

            if (isPrimary) {
                route.setImageUrl(IMAGE_PRE_PATH_FOR_THYMELEAF + picturePath);
                this.routeRepository.save(route);
            } else {
                Picture picture = new Picture();
                picture.setRoute(route);
                picture.setUrl(IMAGE_PRE_PATH_FOR_THYMELEAF + picturePath);
                picture.setAuthor(this.userRepository.findByUsername(this.loggedUser.getUsername())
                        .orElseThrow(() -> new UserNotFoundException("User not found!")));
                picture.setTitle(route.getName());
                this.pictureRepository.save(picture);
                route.getPictures().add(picture);
                this.routeRepository.save(route);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<RouteCategoryDTO> findAllByCategoryName(CategoryNames categoryNames) {
        return this.routeRepository.findAllByCategories_Name(categoryNames)
                .stream().map(route -> this.modelMapper.map(route, RouteCategoryDTO.class))
                .collect(Collectors.toList());
    }

    private String getPicturePath(MultipartFile picture, String routName, boolean isPrimary) {
        String pictureType = picture.getOriginalFilename().split("\\.")[1];

        if (isPrimary) {
            String pathPattern = "%s\\%s.%s";
            return String.format
                    (pathPattern, routNameTransform(routName), UUID.randomUUID(), pictureType);
        }
        String pathPattern = "%s\\%s\\%s.%s";
        return String.format
                (pathPattern, routNameTransform(routName), GALLERY, UUID.randomUUID(), pictureType);
    }

    private String getFilePath(String routName) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                this.loggedUser.getUsername(),
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
