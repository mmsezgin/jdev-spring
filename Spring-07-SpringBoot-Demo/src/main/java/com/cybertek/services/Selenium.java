package com.cybertek.services;

import com.cybertek.inrerfaces.Course;
import org.springframework.stereotype.Component;

@Component
public class Selenium implements Course {

    public int getTeachingHours() {
        return 10;
    }
}
