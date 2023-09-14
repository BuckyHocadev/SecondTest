package com.example.SignUpPage.Utils;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey(){
        KeyPair keyPair = null;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
       
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair; 
    }
}
