package com.ayush.databaseConn.Services;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Exceptions.UserNotFoundException;
import com.ayush.databaseConn.DTO.Mapper;
import com.ayush.databaseConn.Model.Address;
import com.ayush.databaseConn.Model.User;
import com.ayush.databaseConn.DTO.UserRequestDTO;
import com.ayush.databaseConn.Repository.AddressRepository;
import com.ayush.databaseConn.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<UserResponseDTO> listAllUsers() {
        List<User> allUsers =  userRepository.findAll();
        return allUsers.stream().map((user -> Mapper.mapToUserResponseDTO(user))).collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> findUser(String name){
        name = "ayush";
        List<User> foundUser =userRepository.findUserByName(name);
        return  foundUser.stream().map((user)->Mapper.mapToUserResponseDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userDTO) {
        User user = Mapper.mapToUser(userDTO);
        return Mapper.mapToUserResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(int UserId) {
        userRepository.findById(UserId).orElseThrow(()->new UserNotFoundException());
        userRepository.deleteById(UserId);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(int userId, UserRequestDTO updatedUserDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        user.setAge(updatedUserDTO.getAge());
        user.setEmail(updatedUserDTO.getEmail());
        user.setMob_No(updatedUserDTO.getMob_No());
        user.setName(updatedUserDTO.getName());
        //user.setAddresses(updatedUserDTO.getAddresses().stream().map(Mapper::mapToAddress).collect(Collectors.toList()));


        List<AddressRequestDTO> newAddressDTO = updatedUserDTO.getAddresses();
        addressRepository.setIsDeletedTrue(userId);
        for (AddressRequestDTO add: newAddressDTO) {
            if(add.getId()!=null){
                addressRepository.setIsDeletedFalse(add.getId());
                addressService.updateAddress(add.getId(),add);

            }else{addressService.addAddress(add,userId);}
        }
        addressRepository.deleteUnused();



        User updatedUser=userRepository.save(user);
        return Mapper.mapToUserResponseDTO(updatedUser);

    }


}
