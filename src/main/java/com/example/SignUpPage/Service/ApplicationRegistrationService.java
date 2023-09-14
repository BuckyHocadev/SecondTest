package com.example.SignUpPage.Service;

public interface ApplicationRegistrationService {
    Boolean registerUser(String regionCode, String phoneNumber, String password);
    Boolean registerUserWithPhoneNumber(String phoneNumber);
    Boolean checkPhoneNumber(String phoneNumber);
    Boolean isValidPhoneNumber(String phoneNumber, String regionCode);
    Boolean isValidConfirmationMessage(String phoneNumber, String code);
    Boolean isValidPassword(String password, String confirmationPassword);

}
