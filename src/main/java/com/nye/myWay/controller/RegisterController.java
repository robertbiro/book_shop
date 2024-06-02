package com.nye.myWay.controller;

import com.nye.myWay.dto.AuthResponseDTO;
import com.nye.myWay.dto.RegisterDTO;
import com.nye.myWay.entities.AuthenticationResponse;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@AllArgsConstructor
public class RegisterController {
    @Autowired
    private  UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDTO registerDTO, HttpServletResponse httpServletResponse) {
        try {
            AuthenticationResponse authResponse = userService.register(registerDTO);
            setTokenInCookie(httpServletResponse, authResponse.getToken());
            AuthResponseDTO responseMessage = new AuthResponseDTO("Registration successful", authResponse.getToken(), registerDTO.getUsername());
            return ResponseEntity.ok(responseMessage);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }

    // nearly from: https://medium.com/spring-boot/cookie-based-jwt-authentication-with-spring-security-756f70664673
    private void setTokenInCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);
        cookie.setAttribute("token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // csak HTTPS esetén
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1 nap
        response.addCookie(cookie);
    }
}
