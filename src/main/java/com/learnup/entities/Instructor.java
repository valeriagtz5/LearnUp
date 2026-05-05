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
public class Instructor {
    @BsonId
    private ObjectId id;
    
    private String name;
    private String email;
    private List<String> specialities;
    private int experienceYears;
    private String bio;
    private Contact contact;
    private List<ObjectId> coursesTaught;
    
    @BsonProperty("created_in")
    private Instant createdIn;
    
    @BsonProperty("updated_in")
    private Instant updatedIn;

    public Instructor() {
    }

    public Instructor(ObjectId id, String name, String email, List<String> specialities, int experienceYears, String bio, Contact contact, List<ObjectId> coursesTaught, Instant createdIn, Instant updatedIn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialities = specialities;
        this.experienceYears = experienceYears;
        this.bio = bio;
        this.contact = contact;
        this.coursesTaught = coursesTaught;
        this.createdIn = createdIn;
        this.updatedIn = updatedIn;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<ObjectId> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<ObjectId> coursesTaught) {
        this.coursesTaught = coursesTaught;
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
