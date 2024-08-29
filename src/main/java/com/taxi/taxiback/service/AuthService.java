package com.taxi.taxiback.service;

import com.taxi.taxiback.exception.UserException;
import com.taxi.taxiback.request.LoginRequest;
import com.taxi.taxiback.request.SignupRequest;
import com.taxi.taxiback.response.LoginResponse;
import com.taxi.taxiback.response.SignupResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> sendOtp(String email)throws UserException;
    ResponseEntity<SignupResponse> signup(SignupRequest signupRequest) throws UserException;

    ResponseEntity<LoginResponse> login(LoginRequest loginRequest) throws UserException;
}
