package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter //@Data create toString it can cause problem, Stackoverflow with for example Address
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private LocalDateTime createdAt;

    //https://medium.com/@bectorhimanshu/spring-data-jpa-one-to-one-bidirectional-relationship-9ef3674ec2d7
    @OneToOne(mappedBy = "applicationUser",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //bidirectional one-to-one relationship with Address
    private Address address;

    @OneToOne(mappedBy = "applicationUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    //One-To-One Unidirectional Relationship Mapping
    //https://medium.com/@bectorhimanshu/spring-data-jpa-one-to-oneunidirectional-relationship-0c6199bc6e8a
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    /// This means Foreign key will be created only in this table
    // i.e. extra column 'account_id' will be created in the Accountumber table
    //With FetchType.EAGER, it will fetch the details of the Child along with the Parent!!!!!
    private AccountNumber accountNumber;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
