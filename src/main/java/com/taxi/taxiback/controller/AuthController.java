package com.taxi.taxiback.controller;

import com.taxi.taxiback.exception.UserException;
import com.taxi.taxiback.request.OtpRequest;
import com.taxi.taxiback.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/sendotp")
    public ResponseEntity<String> sentOtp(@RequestBody OtpRequest otpRequest) throws UserException {
        return authService.sendOtp(otpRequest.getEmail());
    }
}
