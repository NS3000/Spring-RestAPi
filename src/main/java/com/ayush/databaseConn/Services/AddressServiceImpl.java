package com.ayush.databaseConn.Services;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import com.ayush.databaseConn.DTO.AddressResponseDTO;
import com.ayush.databaseConn.DTO.Mapper;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Exceptions.AddressNotFoundException;
import com.ayush.databaseConn.Exceptions.UserNotFoundException;
import com.ayush.databaseConn.Model.Address;
import com.ayush.databaseConn.Model.User;
import com.ayush.databaseConn.Repository.AddressRepository;
import com.ayush.databaseConn.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<AddressResponseDTO> listAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map((address -> Mapper.mapToAddressResponseDTO(address))).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO addAddress(AddressRequestDTO addressRequestDTO, int userId) {
        Address address = Mapper.mapToAddress(addressRequestDTO);
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());
        user.addAddress(address);
        return  Mapper.mapToUserResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteAddress(int addressId) {
        addressRepository.findById(addressId).orElseThrow(()->new AddressNotFoundException());
        addressRepository.deleteById(addressId);
    }

    @Override
    public List<AddressResponseDTO> getAddress(int userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream().map(Mapper::mapToAddressResponseDTO).collect(Collectors.toList());
    }

    @Override
    public AddressResponseDTO updateAddress(int addressId, AddressRequestDTO addressRequestDTO) {
        Address address = addressRepository.findById(addressId).orElseThrow(()->new AddressNotFoundException());
        address.setAddress(addressRequestDTO.getAddress());
        address.setCity(addressRequestDTO.getCity());
        address.setZip(addressRequestDTO.getZip());
        Address updatedAddress = addressRepository.save(address);
        return Mapper.mapToAddressResponseDTO(updatedAddress);
}



}
