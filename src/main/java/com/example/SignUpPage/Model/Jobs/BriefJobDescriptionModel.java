package com.example.SignUpPage.Model.Jobs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class BriefJobDescriptionModel {
    private Long id;
    private String position;
    private String companyName;
    private String skills;
}
