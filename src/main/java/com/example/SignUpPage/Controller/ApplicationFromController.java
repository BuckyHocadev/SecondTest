package com.example.SignUpPage.Controller;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Model.PasswordDtoModel;
import com.example.SignUpPage.Model.UserPersonalInformationModel;
import com.example.SignUpPage.Service.ApplicationFormService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/SignUp", produces = "application/json")
public class ApplicationFromController {

    // this is the logger
    private static final Logger logger = LoggerFactory.getLogger(ApplicationFromController.class);

    @Autowired
    ApplicationFormService applicationFormService;

//    @GetMapping("/")
//    public String viewHomePage() {
//        System.out.println("im here called: ");
//        return "index";
//    }
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new ApplicationUser());
//        return "signup_form";
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("user", new ApplicationUser());
//        return "login_form";
//    }
//
//    @PostMapping("/process_register")
//    public String processRegister(ApplicationUser user) {
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        String encodedPassword = passwordEncoder.encode(user.getPassword());
////        user.setPassword(encodedPassword);
////        applicationFormService.save(user);
//        return "register_success";
//    }


    // check if the phone number has not registered yet
    @PostMapping("/checkPhoneNumIfExists")
    public ResponseEntity<?> checkPhoneNumber(@RequestBody String phoneNumber) throws JsonProcessingException {
        // Perform backend processing with the phone number
        // Return the response as needed
        logger.info("The DAMN info: {}", phoneNumber);
        if (applicationFormService.checkThePhoneNumberIfExists(phoneNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(" - This phone number has already registered ");
        }
        return ResponseEntity.ok("Ok: ");
    }

    // check if the username is unique

    @PostMapping(value = "/checkUsernameIfExists")
    public ResponseEntity<?> checkUsernameIfExists(@RequestBody String username) throws JsonProcessingException {
        logger.info("The GIVEN USERNAME: {}", username);
        System.out.println("The fucking input: " + username);
        // Perform backend processing with the username
        // Return the response as needed
        if (applicationFormService.checkTheUsernameIfExists(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(" - This username has already registered. Please choose another one ");
        }
        return ResponseEntity.ok("好的：用户名可用: Ok: The username is available");
    }

    // fill the user's personal information
    @PostMapping("/fillUserPersonalInformation")
    public ResponseEntity<?> fillUserPersonalInformation(@RequestBody UserPersonalInformationModel model) {
        if (applicationFormService.addUsersPersonalInformations(model)) {
            return ResponseEntity.ok("Ok: Information's are added ");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("  -- Something is wrong try again");
    }

    @PostMapping("/checkIfPasswordIsValid")
    public ResponseEntity<?> checkIfPasswordIsValid(@RequestBody PasswordDtoModel passwords){
        if(applicationFormService.checkIfPasswordIsValid(passwords)){
            return ResponseEntity.ok("Ok: strong password");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Weak password");
    }
}
