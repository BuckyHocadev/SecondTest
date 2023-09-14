package com.example.SignUpPage.Service;
import com.example.SignUpPage.Model.RegistrationFormModel;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ApplicationUserCrudService {
    Boolean add(RegistrationFormModel model);
    Boolean update(RegistrationFormModel model);
    Boolean delete(String username) throws JsonProcessingException;
}
