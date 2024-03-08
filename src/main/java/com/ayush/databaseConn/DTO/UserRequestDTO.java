package com.ayush.databaseConn.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import org.hibernate.validator.constraints.Range;

public class UserRequestDTO {
    @NotBlank(message = "enter name")
    @Pattern(regexp = "[a-zA-Z\\s]+",message = "invalid Name Format")
    private String name;
    @Email(message = "Invalid Email Format")
    @NotBlank(message = "Enter email")
    private String email;
    @Pattern(regexp = "[0-9]{10}",message = "Invalid Mobile No.")
    @NotBlank(message = "enter Mob_no")
    private String mob_No;
    @Range(min = 18,max = 100,message = "Invalid Age.")
    @NotNull(message = "Enter Age")
    private int age;
    @Valid
    private List<AddressRequestDTO> addresses;

    public UserRequestDTO() {
    }


    public UserRequestDTO(String name, String email, String mob_No, int age, List<AddressRequestDTO> addresses) {
        this.name = name;
        this.email = email;
        this.mob_No = mob_No;
        this.age = age;
        this.addresses = addresses;
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

    public void setMob_no(String mob_no) {
        this.mob_No = mob_no;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<AddressRequestDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequestDTO> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(AddressRequestDTO address){
        addresses.add(address);
    }
}
