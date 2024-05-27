package org.softuni.pathfinder.config;

import org.modelmapper.*;
import org.softuni.pathfinder.exceptions.UserNotFoundException;
import org.softuni.pathfinder.model.dto.rout.AddRouteDTO;
import org.softuni.pathfinder.model.dto.user.UserRegisterDTO;
import org.softuni.pathfinder.model.entity.Category;
import org.softuni.pathfinder.model.entity.Route;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.service.CategoryService;
import org.softuni.pathfinder.service.RoleService;
import org.softuni.pathfinder.session.LoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AppConfig {

    private final CategoryService categoryService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final LoggedUser loggedUser;

    public AppConfig(CategoryService categoryService, UserRepository userRepository, RoleService roleService, LoggedUser loggedUser) {
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.loggedUser = loggedUser;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<AddRouteDTO, Route> typeMap =
                modelMapper.createTypeMap(AddRouteDTO.class, Route.class);

        Provider<User> loggedUserProvider = request -> getLoggedUser();

        Converter<Set<CategoryNames>, Set<Category>> converter =
                context -> context.getSource() == null
                        ? null
                        : categoryService.findByNameIn(context.getSource());
        typeMap
                .addMappings(mapping -> mapping
                        .using(converter)
                        .map(AddRouteDTO::getCategories, Route::setCategories))
                .addMappings(mapping -> mapping
                        .when(Conditions.isNull())
                        .with(loggedUserProvider)
                        .map(AddRouteDTO::getAuthor, Route::setAuthor));

        Provider<User> newUserProvider = request -> new User()
                .setRoles(Set.of(roleService.getRoleByName("USER")))
                .setLevel(Level.BEGINNER);

        Converter<String, String> passwordConverter =
                context -> context.getSource() == null
                        ? null
                        : passwordEncoder().encode(context.getSource());

        modelMapper
                .createTypeMap(UserRegisterDTO.class, User.class)
                .setProvider(newUserProvider)
                .addMappings(mapping -> mapping
                        .using(passwordConverter)
                        .map(UserRegisterDTO::getPassword, User::setPassword));

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private User getLoggedUser() {
        String username = loggedUser.getUsername();
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " was not found!"));
    }
}
