package com.siblingscup.coffee.dto;

import com.siblingscup.coffee.model.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

}
