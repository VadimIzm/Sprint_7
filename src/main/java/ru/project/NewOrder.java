package ru.project;

import java.util.List;

public class NewOrder {
    private String firstName = "Геральт";
    private String lastName = "Ривийский";
    private String address = "Москва, ул. Ивантеевская, д.19, кв.10";
    private int metroStation = 1;
    private String phone = "88005553535";
    private String rentTime = "2";
    private String deliveryDate = "2025-02-12";
    private String comment = "Шевелись, Плотва";
    private List<String> color;

    public NewOrder (List<String> color) {
        this.color = color;
    }

    public NewOrder () {}

    public int getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

}