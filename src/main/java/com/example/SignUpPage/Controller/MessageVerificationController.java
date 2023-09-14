package com.example.SignUpPage.Controller;
import com.example.SignUpPage.Model.VerificationRequest;
import com.example.SignUpPage.Service.MessageVerificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/verification")
@CrossOrigin(origins = "*")
public class MessageVerificationController {
    private static final Logger logger = LoggerFactory.getLogger(MessageVerificationController.class);

    @Autowired
    private MessageVerificationService messageVerificationService;

    // this si to send the confirmation code to phone number
    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody String phoneNum) throws JsonProcessingException {
        String response = String.valueOf(messageVerificationService.send(phoneNum));
        if(response == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Message failed with error:");
        }
        System.out.println("The response: " + response);
        return ResponseEntity.ok(response);
    }

    // this well check the given code whether it is true or not
    @PostMapping("/confirm")
    public ResponseEntity<?> check(@RequestBody VerificationRequest verificationCode) throws JsonProcessingException {
        if(messageVerificationService.check(verificationCode)){
            return ResponseEntity.ok("Verified sent successfully.");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Verification failed with error:");
    }
}
