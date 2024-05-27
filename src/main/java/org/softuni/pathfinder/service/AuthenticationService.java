package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.softuni.pathfinder.model.entity.User;

public interface AuthenticationService {
    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDto);

    void logout();

}
