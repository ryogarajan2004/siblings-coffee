package com.siblingscup.coffee.service;

import com.siblingscup.coffee.dto.LoginRequest;
import com.siblingscup.coffee.dto.RegisterRequest;
import com.siblingscup.coffee.model.User;
import com.siblingscup.coffee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(RegisterRequest request){
        String hash=passwordEncoder.encode(request.getPassword());
        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(hash);
        user.setRole(request.getRole());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public HttpStatus checkUser(LoginRequest request){
        Optional<User>user=userRepository.findByUsername(request.getUsername());

        if(user.isPresent()){
            ;
            return HttpStatus.FOUND;
        }

        else
            return HttpStatus.NOT_FOUND;

    }
}
