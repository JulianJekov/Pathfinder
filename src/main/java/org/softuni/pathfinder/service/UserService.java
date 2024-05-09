package org.softuni.pathfinder.service;

import org.softuni.pathfinder.model.dto.UserLoginDto;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDto userLoginDto);

    void logout();
}
