package com.jbw.jwtAuthentification.service;

import com.jbw.jwtAuthentification.configuration.JwtUtils;
import com.jbw.jwtAuthentification.model.User;
import com.jbw.jwtAuthentification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(User user) {
        //verifier si les user existe deja ou pas
        if(userRepository.findByUsername(user.getUsername()) != null){
            return ResponseEntity.badRequest().body("User is already exist ! ");
        }
        //crypter le password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<?> login(User user) {
        try {
            // authentifier les users
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            // creer un token si l'authentification a reusi
            if (authentication.isAuthenticated()) {
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(user.getUsername()));
                return ResponseEntity.ok(authData);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password or username !");
        } catch (AuthenticationException e) {
           log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password or username !");
        }
    }
}
