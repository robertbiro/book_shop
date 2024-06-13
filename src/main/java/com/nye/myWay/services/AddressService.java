package com.nye.myWay.services;

import com.nye.myWay.dto.AddressDTO;
import com.nye.myWay.entities.Address;
import com.nye.myWay.exception.UserNotFoundException;

import java.security.Principal;

public interface AddressService {

    AddressDTO addAddress(Principal principal, AddressDTO addressDTO) throws UserNotFoundException;
    AddressDTO getAddress(Principal principal) throws UserNotFoundException;
    AddressDTO updateAddress(Principal principal, AddressDTO addressDTO) throws UserNotFoundException;
    AddressDTO deleteAddress(Principal principal) throws UserNotFoundException;
}
