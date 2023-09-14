package com.example.SignUpPage.Service;
import com.example.SignUpPage.Model.PasswordDtoModel;
import com.example.SignUpPage.Model.UserPersonalInformationModel;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ApplicationFormService {
    Boolean checkThePhoneNumberIfExists(String phoneNum) throws JsonProcessingException;
    Boolean checkTheUsernameIfExists(String username) throws JsonProcessingException;
    Boolean addUsersPersonalInformations(UserPersonalInformationModel model);
    Boolean checkIfPasswordIsValid(PasswordDtoModel passwords);
}
