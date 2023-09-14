package com.example.SignUpPage.Service;
import com.example.SignUpPage.Model.VerificationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageVerificationService {
    String send(String phoneNum) throws JsonProcessingException;
    Boolean check(VerificationRequest verificationCode) throws JsonProcessingException;

}
