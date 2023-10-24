package com.project.portnet_be.application.popol.model;

import com.project.portnet_be.dto.popol.carrer.Carrer;
import com.project.portnet_be.dto.popol.popol.Popol;
import com.project.portnet_be.dto.popol.project.Project;

public class PopolModel {

    private Popol popol;
    private Carrer carrer;
    private Project project;

    public Popol getPopol() {
        return popol;
    }

    public void setPopol(Popol popol) {
        this.popol = popol;
    }

    public Carrer getCarrer() {
        return carrer;
    }

    public void setCarrer(Carrer carrer) {
        this.carrer = carrer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
