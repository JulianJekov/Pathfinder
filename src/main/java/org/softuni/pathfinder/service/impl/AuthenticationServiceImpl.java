package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.dto.user.UserRegisterDTO;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.UserRepository;
import org.softuni.pathfinder.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        final User user = this.modelMapper.map(userRegisterDTO, User.class);
        this.userRepository.save(user);
    }
}
