package com.nye.myWay.services;

import com.nye.myWay.dto.AddressDTO;
import com.nye.myWay.entities.Address;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.repositories.AddressRepository;
import com.nye.myWay.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
    //comes from: https://www.geeksforgeeks.org/how-to-use-modelmapper-in-spring-boot-with-example-project/
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AddressDTO addAddress(Principal principal, AddressDTO addressDTO) throws UserNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() ->new UserNotFoundException());
        System.out.println(principal.getName());
        Address address = modelMapper.map(addressDTO, Address.class);
        applicationUser.setAddress(address);
        address.setApplicationUser(applicationUser);
        addressRepository.save(address);
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public AddressDTO getAddress(Principal principal) throws UserNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
        Address address = applicationUser.getAddress();
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }
    //There are numerous ways: https://ololx.medium.com/partial-data-update-in-java-rest-services-1-332fdc621631
    @Override
    public AddressDTO updateAddress(Principal principal, AddressDTO addressDTO) throws UserNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
        Address address = applicationUser.getAddress();

        //s     | s == null | s.isEmpty()          |
        // +-------+-----------+----------------------+
        //null  | true      | NullPointerException |
        // ""   | false     | true                 |
        // "foo"| false     | false

        if (addressDTO.getStreet() != null && !addressDTO.getStreet().isEmpty()) {
            address.setStreet(addressDTO.getStreet());
        }
        if (addressDTO.getCity() != null && !addressDTO.getCity().isEmpty()) {
            address.setCity(addressDTO.getCity());
        }
        if (addressDTO.getState() != null && !addressDTO.getState().isEmpty()) {
            address.setState(addressDTO.getState());
        }
        if (addressDTO.getPostalCode() != null && !addressDTO.getPostalCode().isEmpty()) {
            address.setPostalCode(addressDTO.getPostalCode());
        }
        if (addressDTO.getCountry() != null && !addressDTO.getCountry().isEmpty()) {
            address.setCountry(addressDTO.getCountry());
        }
        Address updatedAddress = addressRepository.save(address);
        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public AddressDTO deleteAddress(Principal principal) throws UserNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
        Address address = applicationUser.getAddress();
        addressRepository.delete(address);
        return getAddress(principal);
    }
}
