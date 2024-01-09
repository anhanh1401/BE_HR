package com.cnpm.hr.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");


        // Kiểm tra jwt
        if(jwt == null || jwt.substring(0,8) != "Bearer "){
            filterChain.doFilter(request, response);
            return;
        }
        else{
        jwt = jwt.substring(7);
            try{
                SecretKey key = Keys.hmacShaKeyFor("hdgsfjhsjdgfkjskskjjhkhfjfvhf12545".getBytes());
                Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
                String employeeEmail = String.valueOf(claims.get("employeeEmail"));
                List<String> roles = claims.get("roles", List.class);
                List<SimpleGrantedAuthority> auths = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                Authentication authentication = new UsernamePasswordAuthenticationToken(employeeEmail, null, auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                throw new BadCredentialsException("token invalid.");
            }
        }
        filterChain.doFilter(request, response);
    }

}
