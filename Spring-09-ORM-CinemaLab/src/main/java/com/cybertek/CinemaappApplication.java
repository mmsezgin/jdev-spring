package com.cybertek;

import com.cybertek.repository.AccountRepository;
import com.cybertek.repository.CinemaRepository;
import com.cybertek.repository.MovieRepository;
import com.cybertek.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CinemaappApplication {
    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaappApplication.class, args);
    }

    @PostConstruct
    public void testAccount(){
        System.out.println(accountRepository.fetchAdminUsers());
    }


}
