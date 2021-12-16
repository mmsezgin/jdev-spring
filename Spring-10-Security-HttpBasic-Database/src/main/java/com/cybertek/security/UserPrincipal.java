package com.cybertek.security;

import com.cybertek.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// Converts user entity to user details, don't know the user yet.
// This is NOT a bean, it is a java class
public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    // getAuthorities() gets them permissions and roles from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // authorityList object is holding both permissions and roles.

        List<GrantedAuthority> authorityList = new ArrayList<>();

        //Extract list of permissions
        user.getPermissionList().forEach(p -> {   //Get each authority, create one object from GrantedAuthority and add it.
            GrantedAuthority authority = new SimpleGrantedAuthority(p); // SimpleGrantedAuthority accepts your authority from user details and adds it to your authority list
            authorityList.add(authority);
        });

        //Extract list of roles
        user.getRoleList().forEach(r ->{ // For each role,
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +r); // ROLE_ spring understands it as role.
            authorityList.add(authority);// hasRole () understands ROLE_ with underscore
        });

        return authorityList;

    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // default is true.

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // default is true.
    // if forgot as false, it will not login and return empty username password screen
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getActive()==1;
    }
}
