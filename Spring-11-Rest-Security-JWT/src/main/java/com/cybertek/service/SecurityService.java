package com.cybertek.service;

import com.cybertek.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
//UserDetailService interface is holding which user it is, Spring understands and keeps the user.
@Service
public class SecurityService implements UserDetailsService {

    private UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }
    // Which user is it, tell me and I will find the user and load
    // Get the user from database, and convert it to user that Spring understands.
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User foundUser = loadUser(s);
        if(foundUser == null){
            throw new UsernameNotFoundException("user not found! " + s);
        }
        // In MVC, we just get the user and give it to UserPrincipal. return new UserPrincipal() . And it will convert to UserDetails
                                                                 //This is the User that Spring understands
        return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(), listAuthorities(foundUser));
    }

    public User loadUser(String value){
        boolean isEmail = value.contains("@");
        return isEmail ? userService.readByEmail(value) : userService.readByUsername(value);
    }

    private List<GrantedAuthority> listAuthorities(User user){
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getRole().name()));
        return grantedAuthorityList;
    }


}
