package com.cybertek.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//This class is created to carry exception message
public class DefaultExceptionMessageDto {

    private String message;
}
