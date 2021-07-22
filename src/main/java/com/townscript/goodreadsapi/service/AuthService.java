package com.townscript.goodreadsapi.service;

import com.townscript.goodreadsapi.Repository.UserRepository;
import com.townscript.goodreadsapi.dto.*;
import com.townscript.goodreadsapi.exception.SpringCustomException;
import com.townscript.goodreadsapi.security.JwtProvider;
import com.townscript.goodreadsapi.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    @Transactional
    public User signUp(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User saved = userRepository.save(user);
        return saved;
    }

    public AuthenticationResponse login(LoginRequest loginRequest){
     Authentication authenticate  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
     SecurityContextHolder.getContext().setAuthentication(authenticate);
     String token = jwtProvider.generateToken(authenticate);

     User u = this.findByEmail(loginRequest.getEmail());

     AuthenticationResponse ar = AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .email(loginRequest.getEmail())
                .userId(u.getUserId())
                .build();
     return ar;
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithEmail(refreshTokenRequest.getEmail());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .email(refreshTokenRequest.getEmail())
                .build();
    }

    @Transactional
    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new SpringCustomException("no user found"));

    }





}
