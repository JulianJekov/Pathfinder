package org.softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.pathfinder.model.entity.Role;
import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


@Service
public class PathfinderUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public PathfinderUserDetailService(UserRepository userRepository, ModelMapper modelMapper) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsername(username)
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    private UserDetails mapToUserDetails(User user) {

       return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(simpleGrantedAuthorities(user.getRoles()))
                .build();
    }

//    public GrantedAuthority grantedAuthority(Role role) {
//        return new SimpleGrantedAuthority("ROLE_" + role.getName().name());
//    }

    public List<SimpleGrantedAuthority> simpleGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .toList();
    }
}
