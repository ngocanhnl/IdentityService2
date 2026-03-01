package com.ngocanhdevteria2.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ngocanhdevteria2.demo.dto.request.UserCreationRequest;
import com.ngocanhdevteria2.demo.dto.response.UserResponse;
import com.ngocanhdevteria2.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties") // Config de test chay bang config trong test.properties || Neu khong co thi no se chay bang file application.yaml o tren
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private LocalDate birthday;

    @BeforeEach //Khoi tao du lieu truoc khi test
    void initData(){
        birthday = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("john123456")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .birthDate(birthday)
                .build();


        userResponse = UserResponse.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .id("jhfsjdfkjdfn")
                .birthDate(birthday)
                .build();
    }


    @Test
    //
    void createUser_validRequest_success() throws Exception {
        // GIVEN: Du lieu dau vao da biet truoc(request, response)
        //Chuyen object request ve chuoi string truoc
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);


        //Khi goi vao user service thi chay o day chu khong chayj trong code that
        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(userResponse);

        // WHEN: request api nao, THEN:expect dieu gi
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1000"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
                        .value("jhfsjdfkjdfn")


        );


    }


    @Test
    void createUser_usernameInvalidRequest_fail() throws Exception {
        // GIVEN: Du lieu dau vao da biet truoc(request, response)
        //Chuyen object request ve chuoi string truoc
        request.setUsername("anh");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);


        //Khi goi vao user service thi chay o day chu khong chayj trong code that
        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(userResponse);

        // WHEN: request api nao, THEN:expect dieu gi
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1003"))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Username must be at least 6 chars")


                );


    }
}
