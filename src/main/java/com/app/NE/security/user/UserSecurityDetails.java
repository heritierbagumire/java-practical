package com.app.NE.security.user;

import com.app.NE.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {
    public String username;
    public String password;
    public boolean isVerified;
    public boolean isActive;

    @Getter
    public List<GrantedAuthority> authorities = new ArrayList<>();

    public UserSecurityDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole().name()));
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

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
