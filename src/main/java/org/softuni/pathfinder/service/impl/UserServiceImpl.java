package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.service.UserService;
import org.softuni.pathfinder.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        final User user = this.modelMapper.map(userRegisterDTO, User.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDto) {

        final String username = userLoginDto.getUsername();

        final User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User with username: " + username + " is not present");
        }

        final boolean passwordMatch = passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());

        if (!passwordMatch) {
            throw new IllegalArgumentException("User entered incorrect password");
        }

        this.loggedUser.login(user.getUsername(), user.getEmail(), user.getFullName());

        return true;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }

    @Override
    public User getLoggedUser() {
        return this.userRepository.findByUsername(loggedUser.getUsername());
    }
}
