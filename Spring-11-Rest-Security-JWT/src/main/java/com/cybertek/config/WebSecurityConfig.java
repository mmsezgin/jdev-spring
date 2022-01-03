package com.cybertek.config;

import com.cybertek.service.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//These two annotations are must fr websecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityFilter securityFilter;
    // Because we do not have form authentication, we need to add authenticationManagerBean()
    // Normally we have login info under config() method but authenticationManagerBean() handles for us.
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] permittedUrls ={
            "/authenticate",
            "/create-user",
            "/api/p1/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf()//cross-site request forgery attack. Sb trying to get your token and mimic your activities.
                .disable() // For API purposes make disable, otherwise gives error.
                .authorizeRequests()
                .antMatchers(permittedUrls)
                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); //


    }
}
