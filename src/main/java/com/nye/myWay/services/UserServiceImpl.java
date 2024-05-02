package com.nye.myWay.services;

import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the UserDetailService");
        if(!username.equals("Ethan")) throw new UsernameNotFoundException("Not Ethan");
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "USER"));
        return new ApplicationUser(1L, "Ethan", passwordEncoder.encode("password"), roles);
    }
}
