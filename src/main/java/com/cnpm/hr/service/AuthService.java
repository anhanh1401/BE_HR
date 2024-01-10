package com.cnpm.hr.service;

import com.cnpm.hr.entity.Session;
import com.cnpm.hr.payload.request.LoginRequest;
import com.cnpm.hr.payload.response.LoginResponse;
import com.cnpm.hr.repository.SessionRepository;
import com.cnpm.hr.security.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    final AuthenticationManager authenticationManager;

    @Autowired
    SessionRepository sessionRepo;

//    final JwtGenerate jwtGenerate;

    public ResponseEntity<LoginResponse> login(LoginRequest request){
        String email = request.getEmail();
        String password = request.getPassword();
        Authentication authentication;
        authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println(roles);
        String jwt = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor("hdgsfjhsjdgfkjskskjjhkhfjfvhf12545".getBytes()))
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .claim("roles", roles)
                .expiration(Date.from(Instant.now().plusSeconds(300000000)))
                .compact();

        Session session = sessionRepo.findByEmployeeEmail(email).orElseGet(() -> {
            Session newSession = new Session();
            newSession.setEmployeeEmail(email);
            return newSession;
        });
        session.setJwt(jwt);
        sessionRepo.save(session);

        return ResponseEntity.ok()
                .body(LoginResponse.builder()
                        .token(jwt)
                        .message("Login success")
                        .build());
    }

    public String logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String employeeEmail = authentication.getName();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String employeeEmail = userDetails.getEmployeeEmail();
        Optional<Session> session = sessionRepo.findByEmployeeEmail(employeeEmail);
        sessionRepo.delete(session.orElse(null));
        return "Logout ed";
    }
}
