package com.ayush.databaseConn.Services;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import com.ayush.databaseConn.DTO.UserRequestDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Exceptions.UserNotFoundException;
import com.ayush.databaseConn.Model.Address;
import com.ayush.databaseConn.Model.User;
import com.ayush.databaseConn.Repository.AddressRepository;
import com.ayush.databaseConn.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;//=mock(UserRepository.class);
    @Mock
    private AddressServiceImpl addressService;//= mock(AddressServiceImpl.class);
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
     AddressRepository addressRepository;// = mock(AddressRepository.class);

    @Test
    public void doesReturnUserByNameTest(){
        Address address1 = new Address(1,"strt","ABAD","122121");
        User user1 = new User(1,"ayush","komal@gmail.com","8989898987",91, List.of(address1));

        String name = "komal";
//        String name = "ayush";
//        when(userRepository.findUserByName(name)).thenReturn(List.of(user1));
        when(userRepository.findUserByName(anyString())).thenReturn(List.of(user1));


        List<UserResponseDTO> users = userService.findUser(name);
        assertThat(users).isNotNull();
        assertThat(users.get(0).getId()).isEqualTo(user1.getId());
    }

    @Test
    public void givesUsersListTest(){

        Address address1 = new Address(1,"strt","ABAD","122121");
        Address address2 = new Address(2,"trt","ABAD","122121");
        User user1 = new User(1,"ayush","ayush@gmail.com","8989898987",91, List.of(address1));
        User user2 = new User(2,"haha","haha@gmail.com","8989676777",92, List.of(address2));

        when(userRepository.findAll()).thenReturn(List.of(user1,user2));

        List<UserResponseDTO> users = userService.listAllUsers();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);

    }

    @Test
    public void doesAddUserTest(){

        Address address1 = new Address(1,"strt","ABAD","122121");
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO(1,"strt","ABAD","122121");

        User user1 = new User(1,"ayush","ayush@gmail.com","8989898987",91, List.of(address1));
        UserRequestDTO saveUser = new UserRequestDTO("ayush","ayush@gmail.com","8989898987",91, List.of(addressRequestDTO));

        when(userRepository.save(any(User.class))).thenReturn(user1);

        UserResponseDTO result = userService.addUser(saveUser);

        assertThat(result).isNotNull();
    }

    @Test
    public void doesDeleteUserTest(){
        int id=1;
        Address address1 = new Address(1,"strt","ABAD","122121");
        User user1 = new User(1,"ayush","ayush@gmail.com","8989898987",91, List.of(address1));

        when(userRepository.findById(id)).thenReturn(Optional.of(user1));
        doNothing().when(userRepository).deleteById(id);

        userService.deleteUser(id);

    }

    @Test
    public void doesUpdateUserTest(){
        int id=1;
        Address address1 = new Address(1,"strt","ABAD","122121");
        AddressRequestDTO addressreq = new AddressRequestDTO(1,"strt","ABAD","122121");

        User user1 = new User(1,"ayush","ayush@gmail.com","8989898987",91, List.of(address1));
        UserRequestDTO updatedUser = new UserRequestDTO("ayush","ayush@gmail.com","8989898987",91, List.of(addressreq));

        when(userRepository.findById(id)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);

        UserResponseDTO result = userService.updateUser(id,updatedUser);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(user1.getId());

    }
}
