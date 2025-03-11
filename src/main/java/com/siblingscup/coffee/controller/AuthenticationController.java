package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.dto.AuthResponse;
import com.siblingscup.coffee.dto.LoginRequest;
import com.siblingscup.coffee.dto.RegisterRequest;
import com.siblingscup.coffee.service.AuthService;
import com.siblingscup.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        
        return ResponseEntity.ok(userService.registerUser(request));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if (userService.checkUser(request).equals(HttpStatus.FOUND))
            return ResponseEntity.ok(new AuthResponse(authService.login(request)));
        else return ResponseEntity.notFound().build();
    }

}
