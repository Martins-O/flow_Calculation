package com.example.flowcalculator.service;

import com.example.flowcalculator.data.dto.AppDto;
import com.example.flowcalculator.data.dto.CreateRequest;
import com.example.flowcalculator.data.model.AppUser;
import com.example.flowcalculator.data.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AppUserServiceImplTest {
    @Autowired
    private AppUserService service;
    //    private final AppUser user;
    public CreateRequest request;
    private AppUserRepository appUser;


    @BeforeEach
    void setUp() {
        request = CreateRequest.builder()
                .lastname("Martins")
                .firstname("Oluwaseun")
                .email("jojololamartins686@gmail.com")
                .username("Martins-O")
                .password("123456789")
                .birthDate("10/04/2022")
                .phoneNumber("08146587069")
                .build();
    }

    @Test
    void creatUser() {
        AppDto dto = service.creatUser(request);
        assertThat(dto).isNotNull();
        assertThat(dto.isSuccess()).isTrue();
    }

    @Test
    void login() {
        AppDto dto = service.login(request.getUsername(), request.getPassword());
        assertThat(dto).isNotNull();
        assertThat(dto.isSuccess()).isTrue();
    }
}