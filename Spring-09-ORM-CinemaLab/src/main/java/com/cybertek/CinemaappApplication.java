package com.cybertek;

import com.cybertek.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CinemaappApplication {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    MovieCinemaRepository movieCinemaRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaappApplication.class, args);
    }

    @PostConstruct
    public void testAccount(){
        System.out.println(accountRepository.fetchAdminUsers());
        System.out.println(cinemaRepository.distinctBYSponsoredName());
        System.out.println(movieCinemaRepository.countAllByCinemaId(4L));
        System.out.println(movieCinemaRepository.retrieveAllByLocationName("AMC Empire 25"));
    }


}
