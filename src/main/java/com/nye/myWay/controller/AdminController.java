package com.nye.myWay.controller;

import com.nye.myWay.dto.AuthResponseDTO;
import com.nye.myWay.dto.LoginDTO;
import com.nye.myWay.entities.AuthenticationResponse;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String helloAdminController() {
        return "Admin level access";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        try {
            AuthenticationResponse authResponse = userService.authenticate(loginDTO);
            setTokenInCookie(response, authResponse.getToken());
            AuthResponseDTO responseMessage = new AuthResponseDTO("Login successful", authResponse.getToken(), loginDTO.getUsername());
            return ResponseEntity.ok(responseMessage);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
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
