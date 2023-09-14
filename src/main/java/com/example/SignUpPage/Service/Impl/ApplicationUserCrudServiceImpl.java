package com.example.SignUpPage.Service.Impl;
import com.example.SignUpPage.Controller.ApplicationFromController;
import com.example.SignUpPage.Repository.ApplicationRepository;
import com.example.SignUpPage.Service.ApplicationUserCrudService;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Model.RegistrationFormModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserCrudServiceImpl implements ApplicationUserCrudService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationFromController.class);
    @Autowired
    ApplicationRepository repository;
    private final PasswordEncoder passwordEncoder;
    public ApplicationUserCrudServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Boolean add(RegistrationFormModel model) {
        ApplicationUser user = new ApplicationUser();
        if (repository.findByUsername(model.getUsername()) != null) {
            return false;
        }
        if(!model.getName().isEmpty() && model.getName() != null){
            user.setName(model.getName());
        }
        if(!model.getUsername().isEmpty() && model.getUsername() != null){
            user.setUsername(model.getUsername());
        }
        if(!model.getSurname().isEmpty() && model.getSurname() != null){
            user.setSurname(model.getSurname());
        }
        if(!model.getPhoneNumber().isEmpty() && model.getPhoneNumber() != null){
            user.setPhoneNumber(model.getPhoneNumber());
        }
        if(!model.getGender().isEmpty() && model.getGender() != null){
            user.setGender(model.getGender());
        }
        if(!model.getCountry().isEmpty() && model.getCountry() != null){
            user.setCountry(model.getCountry());
        }
        repository.save(user);
        return true;
    }

    @Override
    public Boolean update(RegistrationFormModel model) {
        logger.info("The fucking username: {}", model.getUsername());
        ApplicationUser user = new ApplicationUser();
        user = repository.findByPhoneNumberIfExistsForUpdate(model.getPhoneNumber());
        if(!model.getName().isEmpty() && model.getName() != null){
            user.setName(model.getName());
        }
        if(!model.getUsername().isEmpty() && model.getUsername() != null){
            user.setUsername(model.getUsername());
        }
        if(!model.getSurname().isEmpty() && model.getSurname() != null){
            user.setSurname(model.getSurname());
        }
        if(!model.getPhoneNumber().isEmpty() && model.getPhoneNumber() != null){
            user.setPhoneNumber(model.getPhoneNumber());
        }
        if(!model.getGender().isEmpty() && model.getGender() != null){
            user.setGender(model.getGender());
        }
        if(!model.getCountry().isEmpty() && model.getCountry() != null){
            user.setCountry(model.getCountry());
        }
        repository.save(user);
            return true;
    }

    @Transactional
    @Override
    public Boolean delete(String username) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(username);
        String userName = jsonNode.get("username").asText();
        System.out.println("Username: " + userName);
        Optional<ApplicationUser> user = repository.findByUsernameIfExists(userName);
        logger.info("THE USER: {}", user);
        if(user.isPresent()){
            logger.info("the final username: {}", userName.trim());
            repository.deleteByUsername(userName.trim());
            return true;
        }
        return false;
    }
}
