package com.cybertek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //Using antMatcher() we are specifying who will access what
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //request should be authorized
                .antMatchers("index.html").permitAll()   // give access to everyone
                .antMatchers("/profile/**").authenticated() // under profile directory, ask for credentials
                .antMatchers("/admin/**").hasRole("ADMIN")  // under admin directory, every html page under admin, only to those whose role is admin
                .antMatchers("/management/**").hasAnyRole("ADMIN","MANAGER") // TWO ROLES ACCESS : under manager directory, every html page under managerand admin, only to those whose role is admin
                .and()
                .httpBasic(); //perform basic http authentication
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
                .and()
                .withUser("ozzy").password(passwordEncoder().encode("ozzy123")).roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("manager123")).roles("MANAGER"); // Manager is created newly in this module. For manager role.

    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
