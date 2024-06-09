package org.softuni.pathfinder.init;

import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Component
public class TransferGpxCoordinatesToFile implements CommandLineRunner {
    private static final String BASE_GPX_COORDINATES_PATH = "src\\main\\resources\\gpx\\";
    private final RouteRepository repository;

    @Value("${pathfinder.gpx-coordinates.migrate}")
    private Boolean shouldMigrate;

    public TransferGpxCoordinatesToFile(RouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (shouldMigrate) {
            List<Route> routes = this.repository.findAll()
                    .stream()
                    .filter(route -> route.getGpxCoordinates().startsWith("<?xml"))
                    .toList();

            for (Route route : routes) {
                String gpx = route.getGpxCoordinates();
                String filePath = getFilePath(route.getName(), route.getAuthor().getUsername());

                try {
                    File file = new File(BASE_GPX_COORDINATES_PATH + filePath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    OutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(gpx.trim().getBytes());
                    route.setGpxCoordinates(filePath);
                    this.repository.save(route);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getFilePath(String routName, String username) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern, username,
                routNameTransform(routName),
                UUID.randomUUID());
    }

    private String routNameTransform(String routName) {
        return routName.toLowerCase().replaceAll("\\s+", "_")
                .replaceAll("\"", "");
    }

}
