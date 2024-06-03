package com.nye.myWay.controller;

import com.nye.myWay.dto.AddressDTO;
import com.nye.myWay.dto.AddressResponseDTO;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/address")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO, Principal principal) {
        //In Spring security, the principal contains the name of the currently logged-in user.
        //It is an interface called AuthenticatedPrincipal. It has only one method, getName(),
        // which returns the name of the authenticated Principal.
        //comes from: https://www.javaguides.net/2024/04/spring-security-principal.html
        try {
            AddressDTO savedAddressDTO =  addressService.addAddress(principal, addressDTO);
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO("Address added successfully", savedAddressDTO);
            return ResponseEntity.ok(addressResponseDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
    @GetMapping("/get")
    public ResponseEntity<?> getUserAddress(Principal principal) {
        try {
            AddressDTO addressDTO = addressService.getAddress(principal);
            return ResponseEntity.ok(addressDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDTO addressDTO, Principal principal) {
        try {;
            AddressDTO updatedAddress = addressService.updateAddress(principal, addressDTO);
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO("Address updated successfully", updatedAddress);
            return ResponseEntity.ok(addressResponseDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
