package com.nye.myWay.services;

import com.nye.myWay.dto.LoginDTO;
import com.nye.myWay.dto.RegisterDTO;
import com.nye.myWay.entities.AuthenticationResponse;
import com.nye.myWay.exception.UserNameAlreadyTakenException;
import com.nye.myWay.exception.UserNameMissingException;
import com.nye.myWay.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

public interface UserService {
    AuthenticationResponse register (RegisterDTO registerDto) throws UserNameMissingException, UserNameAlreadyTakenException;

    AuthenticationResponse authenticate (LoginDTO loginDTO) throws UserNotFoundException;
}
