package org.softuni.pathfinder.model.entity;

import jakarta.persistence.*;
import org.softuni.pathfinder.model.enums.Level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity{

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    private User author;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany
    @JoinTable(name = "routes_categories", joinColumns =
    @JoinColumn(name = "route_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "categories_id", referencedColumnName = "id"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "route" , fetch = FetchType.EAGER)
    private List<Picture> pictures;

    public Route() {
        this.categories = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public Route setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public Route setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Route setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public Route setName(String name) {
        this.name = name;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Route setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Route setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Route setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Route setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Route setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
