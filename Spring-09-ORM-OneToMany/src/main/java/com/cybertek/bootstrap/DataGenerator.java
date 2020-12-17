package com.cybertek.bootstrap;

import com.cybertek.entity.Address;
import com.cybertek.entity.Person;
import com.cybertek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person("Mike","Smith");
        Person p2 = new Person("Ozzy","Kmith");
        Person p3 = new Person("Tedd","Bbith");

        Address a1 = new Address("King St","22042");
        Address a2 = new Address("Elm St","22102");
        Address a3 = new Address("Java St","22036");






    }
}
