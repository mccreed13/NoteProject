package com.goit.finalproject.dto;

import com.goit.finalproject.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data //TODO added @Data
public class UserDto implements UserDetails { //TODO added implement UserDetails
    private Long id;
    private String username; //TODO login was changed on username
    private String password;

    @Override
    public Collection<Role> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
