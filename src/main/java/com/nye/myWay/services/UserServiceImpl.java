package com.nye.myWay.services;

import com.nye.myWay.dto.LoginDTO;
import com.nye.myWay.dto.RegisterDTO;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.AuthenticationResponse;
import com.nye.myWay.entities.Role;
import com.nye.myWay.exception.UserNameAlreadyTakenException;
import com.nye.myWay.exception.UserNameMissingException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.repositories.UserRepository;
import com.nye.myWay.security.JwtUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private JwtUtilities jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse register (RegisterDTO registerDto) throws UserNameMissingException, UserNameAlreadyTakenException {
        if (registerDto.getUsername() == null || registerDto.getUsername().equals("")) {
            throw new UserNameMissingException();
        } else {
            if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
                throw new UserNameAlreadyTakenException();
            } else {
                ApplicationUser applicationUser = new ApplicationUser();
                applicationUser.setName(registerDto.getName());
                applicationUser.setUsername(registerDto.getUsername());
                applicationUser.setEmail(registerDto.getEmail());
                applicationUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
                applicationUser.setRole(Role.USER);
                userRepository.save(applicationUser);
                String token = jwtTokenProvider.generateToken(applicationUser);
                return new AuthenticationResponse(token);
            }
        }
    }


    public AuthenticationResponse authenticate (LoginDTO loginDTO) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        if(userRepository.findByUsername(loginDTO.getUsername()).isPresent()) {
            String username = String.valueOf(userRepository.findByUsername(loginDTO.getUsername()).get());
            String token = jwtTokenProvider.generateToken( userRepository.findByUsername(loginDTO.getUsername()).get());
            return new AuthenticationResponse(token);
            } else {
            throw new UserNotFoundException();
        }
    }
}
