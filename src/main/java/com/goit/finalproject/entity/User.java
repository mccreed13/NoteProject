package com.goit.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter //TODO Data changed on Setter and Getter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"user\"")
@ToString(exclude = {"note"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 5, max = 50, message = "Username must be between 5 and 50 characters!")
    private String username;

    @Length(max = 250, message = "Password too big!")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
//    private Collection<Role> role; //TODO changed on Set<Role>
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Note> note;

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
