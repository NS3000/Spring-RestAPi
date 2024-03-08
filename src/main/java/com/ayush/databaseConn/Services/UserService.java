package com.ayush.databaseConn.Services;

import com.ayush.databaseConn.DTO.UserRequestDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> listAllUsers();
    UserResponseDTO addUser(UserRequestDTO userDTO);
    void deleteUser(int userId);

    UserResponseDTO updateUser(int UserId, UserRequestDTO userDTO);

    List<UserResponseDTO> findUser(String name);
}
