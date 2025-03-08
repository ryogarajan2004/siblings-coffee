package com.siblingscup.coffee.utility;

import com.siblingscup.coffee.model.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    private static final String SECRET_KEY = "LJFRcKBkOw9Kpx4oafGew1z34q8Yh74MvJhdsWTfcTo=";

    public static String generateToken(String username, Role role) {
        Map<String, Object> claims = new HashMap<>();


        claims.put("role", role.toString());

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
    }

    public static String  extractRole(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().get("role",String.class);
    }


}
