package com.cybertek.services;

import com.cybertek.inrerfaces.ExtraSession;
import org.springframework.stereotype.Component;

@Component
public class OfficeHours implements ExtraSession {
    @Override
    public int getHour() {
        return 2;
    }
}
