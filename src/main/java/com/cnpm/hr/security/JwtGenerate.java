package com.cnpm.hr.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtGenerate {
    SecretKey key = Keys.hmacShaKeyFor("hdgsfjhsjdgfkjskskjjhkhfjfvhf12545".getBytes());
    UserDetailsImpl userDetails;
    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000000))
                .claim("email", auth.getName())
//                .claim("roles", roles)
                .signWith(key).compact();

        return jwt;
    }
}
