package com.ayush.databaseConn.DTO;

import com.ayush.databaseConn.Model.Address;

import java.util.List;

public class UserResponseDTO {

    private int id;
    private String name;
    private String email;
    private String mob_No;
    private int age;
    private List<Address> addresses;

    public UserResponseDTO() {
    }

    public UserResponseDTO(int id, String name, String email, String mob_No, int age, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mob_No = mob_No;
        this.age = age;
        this.addresses=addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob_No() {
        return mob_No;
    }

    public void setMob_No(String mob_No) {
        this.mob_No = mob_No;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
