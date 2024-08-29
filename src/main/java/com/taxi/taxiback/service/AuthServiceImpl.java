package com.taxi.taxiback.service;

import com.taxi.taxiback.exception.UserException;
import com.taxi.taxiback.model.User;
import com.taxi.taxiback.repository.UserRepository;
import com.taxi.taxiback.request.LoginRequest;
import com.taxi.taxiback.request.SignupRequest;
import com.taxi.taxiback.response.LoginResponse;
import com.taxi.taxiback.response.SignupResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserRepository userRepository;
    
    @Override
    public ResponseEntity<String> sendOtp(String email) throws UserException {
        try{
            int randomPin   =(int) (Math.random()*9000)+100000;
            String otp  = String.valueOf(randomPin);
            log.info(otp);
            String emailBody = "Your 6-digit OTP is:" + otp;
            sendEmail(email,"OTP",emailBody);
            return new ResponseEntity<>("OTP Successfully sent on the provided mail",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Wrong Email",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<SignupResponse> signup(SignupRequest signupRequest) throws UserException {
        try{
        User user1 = userRepository.findByEmail(signupRequest.getEmail().toLowerCase());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (user1 == null) {
            User u = new User();
            u.setName(signupRequest.getName());
            u.setEmail(signupRequest.getEmail().toLowerCase());
            u.setMobileNumber(signupRequest.getMobileNumber());
            userRepository.save(u);
            SignupResponse signupResponse=new SignupResponse();
            signupResponse.setMessage("User Registered Successfully");
            signupResponse.setSuccess(true);
            log.info("User Registered successfully");
            return new ResponseEntity<>(signupResponse, HttpStatus.OK);
        } else {
            SignupResponse signupResponse=new SignupResponse();
            signupResponse.setMessage("User Already Present");
            signupResponse.setSuccess(false);
            log.info("User Already Present");
            return new ResponseEntity<>(signupResponse,HttpStatus.OK);
        }
    }catch (Exception e){
        log.error("Error in creating account",e);
        SignupResponse signupResponse=new SignupResponse();
        signupResponse.setMessage("Some error occurred in creating account");
        signupResponse.setSuccess(false);
        return new ResponseEntity<>(signupResponse,HttpStatus.BAD_REQUEST);
    }
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) throws UserException {
            LoginResponse loginResponse=new LoginResponse();
            return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }
    private void sendEmail(String to,String subject,String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        log.info("fddf");
        javaMailSender.send(mailMessage);
    }
}
