package com.cybertek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //request should be authorized
//                .antMatchers("index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")   //changed from hasRole("Admin")
                .antMatchers("/management/**").hasAnyAuthority("ADMIN","MANAGER")  //changed from hasAnyRole ("Admin")
                .and() // and() is just like a separator
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")  //if success login go to index.html
                .failureUrl("/login?error=true") //if unsucccess login go to error
                .permitAll()
                .and() // end of login
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true")
                .and()
                .rememberMe()
                .tokenValiditySeconds(120)  //seconds for the remember me active
                .key("cybertekSecret")
                .userDetailsService(userPrincipalDetailsService); // which user shall I remember, binds key and user. Make all the operations based on this user
    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


}
