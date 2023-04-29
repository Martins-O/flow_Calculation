package com.example.flowcalculator.controller;

import com.example.flowcalculator.data.dto.CreateRequest;
import com.example.flowcalculator.data.dto.TokenResponseDto;
import com.example.flowcalculator.data.model.AppUser;
import com.example.flowcalculator.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(name = "AppUser", path = "api/v1/user/")
public class AppUserController {

    private final AppUserService service;

    @PostMapping(name = "Registration",
            path = "register")
    @Operation(summary = "Create new account")
    public ResponseEntity<?> create(@RequestBody CreateRequest request){
        TokenResponseDto register = service.creatUser(request);
        return ResponseEntity.ok().body(register);
    }

    @PostMapping(name = "Login",
    path = "Login")
    @Operation(summary = "Login")
    public ResponseEntity<?> login(@RequestParam @Valid String username,
                                   @RequestParam @Valid String password) {
        TokenResponseDto login = service.login(username, password);
        return ResponseEntity.ok().body(login);
    }

    @GetMapping(name = "get all",
    path = "get")
    public ResponseEntity<?> getAll() {
        List<AppUser> all = service.getAllUsers();
        return ResponseEntity.ok().body(all);
    }
}
