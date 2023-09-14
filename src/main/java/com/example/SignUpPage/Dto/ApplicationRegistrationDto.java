package com.example.SignUpPage.Dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRegistrationDto {
    private String username;
    private String password;
    private String regionCode;
    private String phoneNumber;
}
