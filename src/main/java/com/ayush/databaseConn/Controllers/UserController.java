package com.ayush.databaseConn.Controllers;

import com.ayush.databaseConn.DTO.UserRequestDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@Tag(name = "User API")
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping("/{name}")
    @Operation(summary = "get user by name")
    public ResponseEntity<List<UserResponseDTO>> findUser(@PathVariable("name") String name){
        return ResponseEntity.ok(userService.findUser(name));
    }

    @GetMapping("/list")
    @Operation(summary = "list users")
    public ResponseEntity<List<UserResponseDTO>> listAllUsers(){
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @PostMapping("/add")
    @Operation(summary = "add user ")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO){
        UserResponseDTO savedUser = userService.addUser(userDTO);
        return new  ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted Successfully.");
    }

    @PutMapping("/{id}")
    @Operation(summary = "edit user by id")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") int userId, @RequestBody @Valid UserRequestDTO updatedUserDTO){
        return new ResponseEntity<UserResponseDTO>(userService.updateUser(userId,updatedUserDTO),HttpStatus.OK);
    }


}
