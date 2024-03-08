package com.ayush.databaseConn.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;
    private String address;
    private String city;
    @Column(nullable = false)
    private String zip;
    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean isDel=false;

    public Address(){};

    public Address(String address, String city, String zip) {
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    public Address(int id,String address, String city, String zip) {
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.id=id;
    }

    public Integer getId() {
        return id;
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

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }


    //    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
