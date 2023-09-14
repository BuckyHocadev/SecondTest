package com.example.SignUpPage.Service.verification.Impl;
import com.example.SignUpPage.Model.verification.VerificationCodeModel;
import com.example.SignUpPage.Repository.verification.VerificationCodeRepository;
import com.example.SignUpPage.Service.verification.VerifcationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifcationCodeServiceImpl implements VerifcationCodeService {
    @Autowired
    VerificationCodeRepository repository;

    @Override
    public void save(VerificationCodeModel model) {
        if(model != null){
            repository.save(model);
        }
    }
}
