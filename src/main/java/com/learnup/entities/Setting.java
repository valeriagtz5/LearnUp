/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.entities;

/**
 *
 * @author valeria
 */
class Setting {
    private boolean notiticatios;
    private boolean darkmode;
    private String language;

    public Setting() {
    }

    public Setting(boolean notiticatios, boolean darkmode, String language) {
        this.notiticatios = notiticatios;
        this.darkmode = darkmode;
        this.language = language;
    }

    public boolean isNotiticatios() {
        return notiticatios;
    }

    public void setNotiticatios(boolean notiticatios) {
        this.notiticatios = notiticatios;
    }

    public boolean isDarkmode() {
        return darkmode;
    }

    public void setDarkmode(boolean darkmode) {
        this.darkmode = darkmode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
}
