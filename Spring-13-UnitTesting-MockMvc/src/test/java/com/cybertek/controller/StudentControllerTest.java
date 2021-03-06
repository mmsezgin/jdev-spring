package com.cybertek.controller;

import com.cybertek.service.StudentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void getStudent_service() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/student").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 0,\"firstName\": \"Mike\",\"lastName\": \"Smith\",\"age\": 20}"))
                .andReturn();
    }

    @Test
    void jsonAssert() throws JSONException {

        String actual = "{\"id\": 0,\"firstName\": \"Mike\",\"lastName\": \"Smith\",\"age\": 20}";
        String expected = "{\"id\": 0,\"firstName\": \"Mike\",\"lastName\": \"Smith\"}";

        JSONAssert.assertEquals(expected,actual,false);
    }

    @Test
    void jsonAssert_withoutEscapeCharacters() throws JSONException {

        String actual = "{id: 0,firstName:Mike,lastName:Smith,age:20}";
        String expected = "{id:0,firstName:Mike,lastName:Smith}";

        JSONAssert.assertEquals(expected,actual,false);
    }

















}