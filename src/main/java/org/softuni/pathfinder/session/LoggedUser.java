package org.softuni.pathfinder.session;

import org.softuni.pathfinder.model.entity.Role;
import org.softuni.pathfinder.model.enums.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Set;

@Component
@SessionScope
public class LoggedUser {
    private String username;

    private String email;

    private String fullName;

    private boolean isLogged;

    private Set<Role> roles;

    public LoggedUser() {
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoggedUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public LoggedUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public LoggedUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public LoggedUser setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public void logout() {
        this.setLogged(false);
        this.setUsername(null);
        this.setEmail(null);
        this.setFullName(null);
    }

    public void login(String username, String email, String fullName, Set<Role> roles) {
        this.setLogged(true);
        this.setUsername(username);
        this.setEmail(email);
        this.setFullName(fullName);
        this.setRoles(roles);
    }

    public boolean isAdmin() {
        return this.roles.stream().anyMatch(role -> role.getName().equals(UserRole.ADMIN));
    }
}
