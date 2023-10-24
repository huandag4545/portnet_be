package com.project.portnet_be.dto.popol.carrer;

import com.project.portnet_be.domain.carrer.CarrerEntity;

import java.util.Date;

public class Carrer {

    private Long carrerId;

    private Long popolId;

    private Date startDt;

    private Date endDt;

    private String company;

    private String department;

    private String contents;

    public Carrer() {
    }

    public Carrer(CarrerEntity carrerEntity) {
        this.carrerId = carrerEntity.getCarrerId();
        this.popolId = carrerEntity.getPopolId();
        this.startDt = carrerEntity.getStartDt();
        this.endDt = carrerEntity.getEndDt();
        this.company = carrerEntity.getCompany();
        this.department = carrerEntity.getDepartment();
        this.contents = carrerEntity.getContents();
    }

    public Long getCarrerId() {
        return carrerId;
    }

    public void setCarrerId(Long carrerId) {
        this.carrerId = carrerId;
    }

    public Long getPopolId() {
        return popolId;
    }

    public void setPopolId(Long popolId) {
        this.popolId = popolId;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }



}
