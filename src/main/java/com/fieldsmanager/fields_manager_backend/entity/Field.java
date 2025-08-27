package com.fieldsmanager.fields_manager_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String images; // store image URLs as text, separated by commas

    @Column(nullable = false)
    private int playersCapacity;

    // Constructors
    public Field() {}

    public Field(String name, String images, int playersCapacity) {
        this.name = name;
        this.images = images;
        this.playersCapacity = playersCapacity;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getPlayersCapacity() {
        return playersCapacity;
    }

    public void setPlayersCapacity(int playersCapacity) {
        this.playersCapacity = playersCapacity;
    }
}
