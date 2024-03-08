package com.ayush.databaseConn.Controllers;

import com.ayush.databaseConn.DTO.AddressRequestDTO;
import com.ayush.databaseConn.DTO.AddressResponseDTO;
import com.ayush.databaseConn.DTO.UserResponseDTO;
import com.ayush.databaseConn.Services.AddressService;
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
@RequestMapping("/address")
@Tag(name = "Address Api" )
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{addressId}")
    @Operation(summary = "gets address by id")
    public ResponseEntity<List<AddressResponseDTO>> findAddress(@PathVariable("addressId") int addressId){
        return ResponseEntity.ok(addressService.getAddress(addressId));
    }

    @GetMapping("/list")
    @Operation(summary = "list all addresses")
    public ResponseEntity<List<AddressResponseDTO>> listAllAddresses(){
        return ResponseEntity.ok(addressService.listAllAddresses());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete address by id")
    public ResponseEntity<String> deleteAddress(@PathVariable("id")int id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address Deleted Successfully");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit an address by id")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable("id") int id, @RequestBody @Valid AddressRequestDTO updatedAddress){

        return new ResponseEntity<>(addressService.updateAddress(id,updatedAddress),HttpStatus.OK);

    }
//----------------------------------------------------------
    @PostMapping("/addTo/{id}")
    @Operation(summary = "add an Address to a User")
    public ResponseEntity<UserResponseDTO> addAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO, @PathVariable("id") int id){
        UserResponseDTO savedAddress = addressService.addAddress(addressRequestDTO,id);
        return new ResponseEntity<>(savedAddress,HttpStatus.OK);
    }

}
