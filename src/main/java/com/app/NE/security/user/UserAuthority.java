package com.app.NE.security.user;

import com.app.NE.enums.ERole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class UserAuthority implements GrantedAuthority {
    @Getter
    private UUID id;

    public ERole authority;

    public UserAuthority(UUID id, ERole authority) {
        this.id = id;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.toString();
    }
}
