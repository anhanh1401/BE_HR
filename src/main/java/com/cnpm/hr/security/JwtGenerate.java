package com.cnpm.hr.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtGenerate {
    SecretKey key = Keys.hmacShaKeyFor("hdgsfjhsjdgfkjskskjjhkhfjfvhf12545".getBytes());
    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 864000000))
                .claim("email", auth.getName())
                .signWith(key).compact();

        return jwt;
    }
}
