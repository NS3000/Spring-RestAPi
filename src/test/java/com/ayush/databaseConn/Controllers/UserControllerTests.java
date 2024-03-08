package com.ayush.databaseConn.Controllers;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import com.ayush.databaseConn.DTO.UserRequestDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Exceptions.UserNotFoundException;
import com.ayush.databaseConn.Model.Address;
import com.ayush.databaseConn.Repository.UserRepository;
import com.ayush.databaseConn.Services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.assertj.core.api.Assertions.*;


import java.util.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Test
    public void doesReturnUserTest() throws Exception{
        String validName = "test";
        Address address =(new Address(1,"123 BEZ","ABAD","123455"));
        UserResponseDTO user = new UserResponseDTO(1,"test","as@gmail.com","1234567899",22,List.of(address));

        when(userService.findUser(validName)).thenReturn(List.of(user));


        //For Valid Name

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{name}",validName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].id").value(user.getId()))

                .andDo(MockMvcResultHandlers.print());

        //For User Not Found
        String invalidName = "notTest";

        when(userService.findUser(invalidName)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{name}",invalidName))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void doesCreateUserExceptionNameTest() throws Exception
    {
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO("123 BEZ","ABAD","123455");
        UserRequestDTO validUserRequestDTO = new UserRequestDTO("ayush","ayush11@gmail.com","7487644979",91, Collections.singletonList(addressRequestDTO));
        UserRequestDTO invalidUserRequestDTO= new UserRequestDTO("ayush---","ayush11@gmail.com","7487644979",91, Collections.singletonList(addressRequestDTO));


        //for invalid input
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserRequestDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


        //for valid Input
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUserRequestDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void doesReturnUsersListTest() throws Exception {

        Address address1 = new Address("123 BEZ","ABAD","123455");
        List<UserResponseDTO> users = new ArrayList<>(Arrays.asList(
                new UserResponseDTO(1,"ayush","ayush11@gmail.com","7487644979",91, Collections.singletonList(address1)),
                new UserResponseDTO(1,"vv","vv2@gmail.com","7412345579",91, Collections.singletonList(address1))
        ));

        when(userService.listAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void doesDeleteUserTest() throws Exception{

        int id = 1;

        doNothing().when(userService).deleteUser(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("User Deleted Successfully."));
    }

    @Test
    public void doesUpdateUserTest() throws Exception{

        int id = 1;

        Address address = new Address(1,"NEW","NEW","111111");
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO(1,"NEW","NEW","111111");
        UserRequestDTO updatedUser = new UserRequestDTO("updated","updated@gmail.com","1111111111",91, Collections.singletonList(addressRequestDTO));
        UserResponseDTO userResponse = new UserResponseDTO(id,"updated","updated@gmail.com","1111111111",91, Collections.singletonList(address));
//
//
        when(userService.updateUser(id,updatedUser)).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
