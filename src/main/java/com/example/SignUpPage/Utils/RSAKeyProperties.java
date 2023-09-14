package com.example.SignUpPage.Utils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@Setter
@Component
public class RSAKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateCrtKey privateKey;

    public RSAKeyProperties(){
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateCrtKey) pair.getPrivate();
    }

}
