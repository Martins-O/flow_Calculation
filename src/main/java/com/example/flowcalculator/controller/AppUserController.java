package com.example.flowcalculator.controller;

import com.example.flowcalculator.data.dto.AppDto;
import com.example.flowcalculator.data.dto.CreateRequest;
import com.example.flowcalculator.data.model.AppUser;
import com.example.flowcalculator.service.AppUserService;
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
    public ResponseEntity<?> create(@RequestBody CreateRequest request){
        AppDto register = service.creatUser(request);
        return ResponseEntity.ok().body(register);
    }

    @PostMapping(name = "Login",
    path = "Login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password) {
        AppDto login = service.login(username, password);
        return ResponseEntity.ok().body(login);
    }

    @GetMapping(name = "get all",
    path = "get")
    public ResponseEntity<?> getAll() {
        List<AppUser> all = service.getAllUsers();
        return ResponseEntity.ok().body(all);
    }
}
