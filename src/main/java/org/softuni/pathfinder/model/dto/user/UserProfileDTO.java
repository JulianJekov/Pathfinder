package org.softuni.pathfinder.model.dto.user;

import org.softuni.pathfinder.model.enums.Level;

public class UserProfileDTO {

    private Level level;

    private String fullName;

    private String username;

    private Integer age;

    public Level getLevel() {
        return level;
    }

    public UserProfileDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfileDTO setAge(Integer age) {
        this.age = age;
        return this;
    }
}
