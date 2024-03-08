package com.ayush.databaseConn.Services;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import com.ayush.databaseConn.DTO.AddressResponseDTO;
import com.ayush.databaseConn.DTO.UserRequestDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Model.Address;

import java.util.List;

public interface AddressService {

    List<AddressResponseDTO> listAllAddresses();
    UserResponseDTO addAddress(AddressRequestDTO addressRequestDTO,int userId);
    void deleteAddress(int addressId);

    List<AddressResponseDTO> getAddress(int addressId);

    AddressResponseDTO updateAddress(int addressId, AddressRequestDTO addressRequestDTO);


}
