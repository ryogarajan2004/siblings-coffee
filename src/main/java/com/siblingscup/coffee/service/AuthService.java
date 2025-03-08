package com.siblingscup.coffee.service;


import com.siblingscup.coffee.dto.LoginRequest;
import com.siblingscup.coffee.model.User;
import com.siblingscup.coffee.repository.UserRepository;
import com.siblingscup.coffee.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder encoder;



    @Autowired
    public AuthService(PasswordEncoder encoder){
        this.encoder=encoder;
    }

public String encryptPassword(String password){
        return encoder.encode(password);
}
    public String login(LoginRequest request){

        String username=request.getUsername();
        String password=request.getPassword();
        Optional<User> user=userRepository.findByUsername(username);


        if(!encoder.matches(password,user.get().getPassword())){
            throw new RuntimeException("Invalid username or password");
        }

        return JwtUtil.generateToken(user.get().getUsername(),user.get().getRole());
    }


}
