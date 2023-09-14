package com.example.SignUpPage.Model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationFormModel {

    private String name;

    private String surname;

    private String username;

    private String country;

    private String gender;

    private String phoneNumber;

}