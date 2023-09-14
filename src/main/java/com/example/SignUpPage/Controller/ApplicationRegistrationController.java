package com.example.SignUpPage.Controller;
import com.example.SignUpPage.Dto.ApplicationLoginResponseDto;
import com.example.SignUpPage.Dto.ApplicationLoginVerificationDto;
import com.example.SignUpPage.Dto.ApplicationRegistrationDto;
import com.example.SignUpPage.Service.ApplicationRegistrationService;
import com.example.SignUpPage.Service.Impl.ApplicationAuthenticationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class ApplicationRegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationRegistrationController.class);
    @Autowired
    private ApplicationRegistrationService applicationRegistrationService;
    @Autowired
    private ApplicationAuthenticationServiceImpl applicationAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody ApplicationRegistrationDto dto){

//        if(!applicationRegistrationService.isValidPhoneNumber(dto.getPhoneNumber(), dto.getRegionCode())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid phone number !!!!");
//        }

        if(applicationRegistrationService.checkPhoneNumber(dto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number already registered !!!");
        }

        Boolean response = applicationRegistrationService.registerUserWithPhoneNumber(dto.getPhoneNumber());

        if(response){
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration failed with error:");
    }


    @PostMapping("/register_confirm")
    public ResponseEntity<?> checkVerficationCode(@RequestBody ApplicationLoginVerificationDto message){

        System.out.println("The phone number: " + message.getPhoneNumber() + " the Code: " + message.getCode() + " the others " + message.getConfirmationPassword());
        if(!applicationRegistrationService.isValidConfirmationMessage(message.getPhoneNumber(), message.getCode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid verification code.");
        }
        logger.info("Confirmation message verified. and  We came to password verification");
        if(!applicationRegistrationService.isValidPassword(message.getPassword(), message.getConfirmationPassword())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Password does not match. Please type again");
        }
        logger.info("The passwords format verified. and WE came to save the user");
        if(applicationRegistrationService.registerUser(message.getRegionCode(), message.getPhoneNumber(), message.getPassword())){
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Oops !!! , Something went wrong");
    }

    @PostMapping("/login")
    public ApplicationLoginResponseDto loginUser(@RequestBody ApplicationRegistrationDto body){
        System.out.println("This line is working " + body.getUsername() + " : " + body.getPhoneNumber() + " : " + body.getPassword());
        return applicationAuthenticationService.loginUser(body.getPhoneNumber(), body.getPassword());
    }
}
