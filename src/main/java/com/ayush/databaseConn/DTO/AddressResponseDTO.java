package com.ayush.databaseConn.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddressResponseDTO {

    private int id;
    private String address;
    private String city;
    private String zip;

    public int getId() {
        return id;
    }

    public AddressResponseDTO(int id, String address, String city, String zip) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
