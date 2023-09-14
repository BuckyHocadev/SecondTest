package com.example.SignUpPage.Utils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class VerificationCode {
    public String generateCode(){
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        int randomNumber = randomDataGenerator.nextInt(100000, 999999);
        System.out.println("Random Number with 6 digits: " + randomNumber);
        return String.valueOf(randomNumber);
    }
}
