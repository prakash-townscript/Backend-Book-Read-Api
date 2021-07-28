package com.townscript.goodreadsapi.controller;

import com.townscript.goodreadsapi.dto.LoginRequest;
import com.townscript.goodreadsapi.dto.RefreshTokenRequest;
import com.townscript.goodreadsapi.dto.RegisterRequest;
import com.townscript.goodreadsapi.service.AuthService;
import com.townscript.goodreadsapi.service.RefreshTokenService;
import com.townscript.goodreadsapi.util.ApiResponse;
import com.townscript.goodreadsapi.util.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/signup")
    public ApiResponse signup(@RequestBody RegisterRequest registerRequest){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = authService.signUp(registerRequest);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data =  authService.login(loginRequest);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @PostMapping("/refresh/token")
    public ApiResponse refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = authService.refreshToken(refreshTokenRequest);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @PostMapping("/logout")
    public ApiResponse logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

}
