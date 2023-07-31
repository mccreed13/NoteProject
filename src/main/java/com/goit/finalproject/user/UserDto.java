package com.goit.finalproject.user;

import com.goit.finalproject.entity.Role;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@Data //TODO added @Data
public class UserDto implements UserDetails { //TODO added implement UserDetails
    private Long id;
    private String username; //TODO login was changed on username
    private String password;

    @Override
    public Collection<Role> getAuthorities() { //TODO was null before
        return new HashSet<Role>(Arrays.asList(new Role()));
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
