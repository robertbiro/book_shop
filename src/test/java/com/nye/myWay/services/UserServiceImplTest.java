package com.nye.myWay.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername_ValidUsername() {
        String username = "Ethan";
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_InvalidUsername() {
        String username = "John";
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        try {
            userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            assertEquals("Not Ethan", e.getMessage());
        }
    }
}
