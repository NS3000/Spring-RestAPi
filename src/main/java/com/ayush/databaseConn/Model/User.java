package com.ayush.databaseConn.Model;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mob_No;
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> addresses;

    public User() {
    }

    public User(int id, String name, String email, String mob_No, int age, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mob_No = mob_No;
        this.age = age;
        this.addresses = addresses;
    }

    public User(String name, String email, String mob_No, int age, List<Address> addresses) {
        this.name = name;
        this.email = email;
        this.mob_No = mob_No;
        this.age = age;
        this.addresses=addresses;
    }

    public int getId() {
        return id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMob_No() {
        return mob_No;
    }

    public void setMob_No(String mob_No) {
        this.mob_No = mob_No;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }
    public void removeAddress(Address address){
        addresses.remove(address);
    }
}
