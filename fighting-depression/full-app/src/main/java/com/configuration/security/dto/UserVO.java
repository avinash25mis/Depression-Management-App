package com.configuration.security.dto;

import com.model.common.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author avinash.a.mishra
 */
public class UserVO implements UserDetails {

   private String username;
   private String password;
   private boolean active;
   private List<GrantedAuthority> grantedAuthorityList;

    public UserVO(AppUser user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.active=user.isActive();
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getUserRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        this.grantedAuthorityList=authorities;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
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
        return active;
    }
}
