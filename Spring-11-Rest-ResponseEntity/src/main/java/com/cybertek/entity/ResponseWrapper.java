package com.cybertek.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
// Describe here how JSON look like. Here CUSTOMIZED. This file might be under different folder other than entity
@JsonInclude(JsonInclude.Include.NON_NULL)  //If it is NULL, ignore.
public class ResponseWrapper {

    private boolean success;
    private String message;
    private Integer code;
    private Object data;

    public ResponseWrapper(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code= HttpStatus.OK.value();
        this.success=true;
    }

    public ResponseWrapper(String message) {
        this.message = message;
        this.code=HttpStatus.OK.value();
        this.success=true;
    }
}
