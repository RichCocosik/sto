package com.project.sto.domain;

public class Worker {

    private Integer id;
    private String fio;
    private Integer phoneNumber;
    private String password;
    private Boolean admin;

    public Worker(Integer id, String fio, Integer phoneNumber, String password, Boolean admin) {
        this.id = id;
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.admin = admin;
    }

    public Worker(String fio, Integer phoneNumber, String password, Boolean admin) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.admin = admin;
    }

    public Worker() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
