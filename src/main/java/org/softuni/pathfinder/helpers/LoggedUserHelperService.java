package org.softuni.pathfinder.helpers;

import org.softuni.pathfinder.model.entity.User;
import org.softuni.pathfinder.model.enums.UserRole;
import org.softuni.pathfinder.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoggedUserHelperService {

    private final UserRepository userRepository;

    public LoggedUserHelperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        String username = getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public boolean isLogged() {
        return getAuthentication().isAuthenticated();
    }

    public boolean isAdmin() {
        return hasRol(UserRole.ADMIN);
    }

    public boolean isModerator() {
        return hasRol(UserRole.MODERATOR);
    }

    public boolean isOnlyUser() {
        return this.getAuthentication()
                .getAuthorities()
                .stream()
                .allMatch(role -> role.getAuthority().equals("ROLE_" + UserRole.USER));
    }

    private boolean hasRol(UserRole userRole) {
        return this.getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_" + userRole));
    }

    public String getUsername() {
        return getUserDetails().getUsername();
    }

    private UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
