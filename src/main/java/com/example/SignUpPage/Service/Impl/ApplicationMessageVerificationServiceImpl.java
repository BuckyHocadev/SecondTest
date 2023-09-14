package com.example.SignUpPage.Service.Impl;
import com.example.SignUpPage.Controller.ApplicationFromController;
import com.example.SignUpPage.Service.MessageVerificationService;
import com.example.SignUpPage.Model.VerificationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.client.VonageClient;
import com.vonage.client.verify.CheckResponse;
import com.vonage.client.verify.VerifyResponse;
import com.vonage.client.verify.VerifyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service
@PropertySource("classpath:application.properties")
public class ApplicationMessageVerificationServiceImpl implements MessageVerificationService {

    @Value("${message.voyage.api.key}")
    private String apiKey;
    @Value("${message.voyage.api.secret}")
    private String apiSecret;

    private static final Logger logger = LoggerFactory.getLogger(ApplicationFromController.class);

    @Override
    public String send(String phoneNum) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(phoneNum);
        String phoneNumber = jsonNode.get("phoneNumber").asText();
        System.out.println("the: " + phoneNumber + " The key: " + apiKey + " The secret: " + apiSecret);
        VonageClient client = VonageClient.builder().apiKey(apiKey).apiSecret(apiSecret).build();
        VerifyResponse response = client.getVerifyClient().verify(phoneNumber, "Vonage");

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("RequestID: %s\n", response.getRequestId());
            return String.valueOf(response.getRequestId());
        }
        System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
        return null;
    }

    @Override
    public Boolean check(VerificationRequest verificationCode) throws JsonProcessingException {
        String requestID = verificationCode.getRequestId();
        String code = verificationCode.getCode();
        System.out.println("\nThe id: " + requestID + " code: " + code);
        VonageClient client = VonageClient.builder().apiKey("f013b32d").apiSecret("LBZhWe6hapP5mT2Y").build();
        CheckResponse response = client.getVerifyClient().check(requestID, code);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.println("Verification Successful");
            return true;
        }
        System.out.println("Verification failed: " + response.getErrorText());
        return false;
    }
}
