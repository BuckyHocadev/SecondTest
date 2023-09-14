package com.example.SignUpPage.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLoginVerificationDto {
    private String regionCode;
    private String phoneNumber;
    private String code;
    private String password;
    private String confirmationPassword;
}
