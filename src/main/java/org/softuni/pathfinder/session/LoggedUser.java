package org.softuni.pathfinder.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private String username;

    private String email;

    private String fullName;

    private boolean isLogged;

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

    public void logout() {
        this.setLogged(false);
        this.setUsername(null);
        this.setEmail(null);
        this.setFullName(null);
    }

    public void login(String username, String email, String fullName) {
        this.setLogged(true);
        this.setUsername(username);
        this.setEmail(email);
        this.setFullName(fullName);
    }
}
