package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class UserModel extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String avatarUrl;
    private Boolean isLocked;

    @ElementCollection
    private Set<UserRole> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userModel",fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "userModel",fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "userModel",fetch = FetchType.LAZY)
    private Set<Rating> ratings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean addRole(UserRole role){
        return roles.add(role);
    }
}
