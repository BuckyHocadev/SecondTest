package com.example.SignUpPage.Model;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPersonalInformationModel {

        @NotNull
        @NotEmpty(message = "Name can not be empty")
        private String name;

        @NotNull
        @NotEmpty(message = "Surname can not be empty")
        private String surname;

        @NotNull
        @Column(unique = true)

        @NotEmpty(message = "Username can not be empty")
        private String username;

        @NotNull
        @NotEmpty(message = "Country can not be empty")
        private String country;

        @NotNull
        @NotEmpty(message = "Please select your gender")
        private String gender;

}
