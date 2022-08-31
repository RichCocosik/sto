package com.project.sto.domain;

import com.project.sto.dao.impl.UserRepositoryImpl;

import java.time.LocalDate;

public class WorkDay {

    private Integer id;
    private String name;
    private Integer userId;
    private Integer serviceId;
    private LocalDate date;
    private Double purchase;

    public WorkDay(Integer id, String name, Integer userId, Integer serviceId, LocalDate date, Double purchase) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.serviceId = serviceId;
        this.date = date;
        this.purchase = purchase;
    }

    public WorkDay(String name, Integer userId, Integer serviceId, LocalDate date, Double purchase) {
        this.name = name;
        this.userId = userId;
        this.serviceId = serviceId;
        this.date = date;
        this.purchase = purchase;
    }

    public WorkDay() {
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPurchase() {
        return purchase;
    }

    public void setPurchase(Double purchase) {
        this.purchase = purchase;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                ", purchase=" + purchase +
                '}';
    }
}
