/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.entities;

import java.time.Instant;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author valeria
 */
public class Course {
    @BsonId
    private ObjectId id;
    
    private String title;
    private String category;
    private ObjectId instructorId;
    private Double price;
    private Double rating;
    private List<String> tags;
    private List<Module> modules;
    
    @BsonProperty("created_in")
    private Instant createdIn;
    
    @BsonProperty("updated_in")
    private Instant updatedIn;

    public Course() {
    }

    public Course(ObjectId id, String title, String category, ObjectId instructorId, Double price, Double rating, List<String> tags, List<Module> modules, Instant createdIn, Instant updatedIn) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.instructorId = instructorId;
        this.price = price;
        this.rating = rating;
        this.tags = tags;
        this.modules = modules;
        this.createdIn = createdIn;
        this.updatedIn = updatedIn;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ObjectId getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(ObjectId instructorId) {
        this.instructorId = instructorId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Instant getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Instant createdIn) {
        this.createdIn = createdIn;
    }

    public Instant getUpdatedIn() {
        return updatedIn;
    }

    public void setUpdatedIn(Instant updatedIn) {
        this.updatedIn = updatedIn;
    }
    
}
