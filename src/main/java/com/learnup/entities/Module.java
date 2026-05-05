/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.entities;

import java.util.List;

/**
 *
 * @author valeria
 */
public class Module {
    private String moduleTitle;
    private int durationMinutes;
    private List<Resource> resources;

    public Module() {
    }

    public Module(String moduleTitle, int durationMinutes, List<Resource> resources) {
        this.moduleTitle = moduleTitle;
        this.durationMinutes = durationMinutes;
        this.resources = resources;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    
}
