package com.example.SignUpPage.Dto;
import com.example.SignUpPage.Model.ApplicationUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationLoginResponseDto {
    private ApplicationUser user;
    private String jwt;
    public ApplicationLoginResponseDto(String token) {
        this.jwt = token;
    }

    public ApplicationLoginResponseDto(ApplicationUser s, String token) {
        this.user = s;
        this.jwt = token;
    }
}
