package com.ayush.databaseConn.DTO;

import com.ayush.databaseConn.Model.Address;
import com.ayush.databaseConn.Model.User;

public class Mapper {

    public static User mapToUser(UserRequestDTO userDTO){
        return new User(userDTO.getName(), userDTO.getEmail(), userDTO.getMob_No(), userDTO.getAge(),userDTO.getAddresses().stream().map(Mapper::mapToAddress).toList());

    };
    public static UserResponseDTO mapToUserResponseDTO(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getMob_No(), user.getAge(),user.getAddresses());

    };
    public static Address mapToAddress(AddressRequestDTO addressRequestDTO){
        return new Address(addressRequestDTO.getAddress(),addressRequestDTO.getCity(),addressRequestDTO.getZip());
    }
    public static AddressResponseDTO mapToAddressResponseDTO(Address address){
        return new AddressResponseDTO(address.getId(),address.getAddress(),address.getCity(),address.getZip());
    }
}
