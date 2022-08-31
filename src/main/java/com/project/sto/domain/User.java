package com.project.sto.domain;

public class User {

    private Integer id;
    private String fio;
    private Integer phoneNumber;
    private Integer counter;
    private Double purchases;

    public User(Integer id, String fio, Integer phoneNumber, Integer counter, Double purchases) {
        this.id = id;
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.counter = counter;
        this.purchases = purchases;
    }

    public User(String fio, Integer phoneNumber, Integer counter, Double purchases) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.counter = counter;
        this.purchases = purchases;
    }

    public User() {
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

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Double getPurchases() {
        return purchases;
    }

    public void setPurchases(Double purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", counter=" + counter +
                ", purchases=" + purchases +
                '}';
    }
}
