package com.ayush.databaseConn.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddressRequestDTO {

    private Integer id;
    @NotBlank(message = "Enter Address")
    @Pattern(regexp = "[a-zA-Z0-9,.\\-\\s]+",message = "Invalid Address")
    private String address;
    @NotBlank(message = "Enter City")
    @Pattern(regexp = "[a-zA-Z\\s]+",message = "Enter valid City")
    private String city;
    @NotBlank(message = "Enter ZipCode")
    @Pattern(regexp = "[0-9]{6}",message = "Enter Valid ZipCode")
    private String zip;

    public AddressRequestDTO(){}

    public AddressRequestDTO(String address, String city, String zip) {
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    public AddressRequestDTO(Integer id, String address, String city, String zip) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zip = zip;
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
     public Integer getId(){ return id;}
}
