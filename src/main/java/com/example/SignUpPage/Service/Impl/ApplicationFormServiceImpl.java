package com.example.SignUpPage.Service.Impl;
import com.example.SignUpPage.Controller.ApplicationFromController;
import com.example.SignUpPage.Repository.ApplicationRepository;
import com.example.SignUpPage.Service.ApplicationFormService;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Model.PasswordDtoModel;
import com.example.SignUpPage.Model.UserPersonalInformationModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationFromController.class);

    @Autowired
    ApplicationRepository repository;


    @Override
    public Boolean checkThePhoneNumberIfExists(String phoneNum) throws JsonProcessingException {
        Optional<ApplicationUser> user = repository.findByPhoneNumberIfExists(phoneNum);
        return user.isPresent();
    }

    @Override
    public Boolean checkTheUsernameIfExists(String username) throws JsonProcessingException {
        Optional<ApplicationUser> user = repository.findByUsernameIfExists(username);
        logger.info("the username: {}", user);
        return user.isPresent();
    }

    @Override
    public Boolean addUsersPersonalInformations(UserPersonalInformationModel model) {
        return null;
    }

    @Override
    public Boolean checkIfPasswordIsValid(PasswordDtoModel passwords) {
        return null;
    }
}