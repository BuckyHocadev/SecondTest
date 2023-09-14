package com.example.SignUpPage.Service.Impl;
import com.alibaba.fastjson.JSON;
import com.example.SignUpPage.Repository.ApplicationRepository;
import com.example.SignUpPage.Repository.RoleRepository;
import com.example.SignUpPage.Repository.verification.VerificationCodeRepository;
import com.example.SignUpPage.Service.ApplicationRegistrationService;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Model.RoleModel;
import com.example.SignUpPage.Model.request.SmsSendRequest;
import com.example.SignUpPage.Model.response.SmsSendResponse;
import com.example.SignUpPage.Model.verification.VerificationCodeModel;
import com.example.SignUpPage.Utils.ChuangLanSmsUtil;
import com.example.SignUpPage.Utils.VerificationCode;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {

    // 用户平台API账号(非登录账号,示例:N1234567)
    @Value("${message.chuanglan.api.account}")
    private  String account;
    // 用户平台API密码(非登录密码)
    @Value("${message.chaunglan.api.password}")
    private  String password;

    public static final String charset = "utf-8";

    @Autowired
    private VerificationCode verificationCode;

    @Autowired
    private ApplicationRepository repository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Override
    public Boolean registerUser(String regionCode, String phoneNumber, String password) {

        Random random = new Random();
        int randomUsername = random.nextInt(99999999) + 10000000;
        ApplicationUser user = new ApplicationUser();
        Set<RoleModel> authorities = new HashSet<>();
        Optional<ApplicationUser> existingUser = repository.findByPhoneNumberIfExists(phoneNumber);

        if(existingUser.isPresent()){
            ApplicationUser updateUser = existingUser.get();
            updateUser.setPassword(passwordEncoder.encode(password));
            updateUser.setAuthorities(authorities);
            updateUser.setPhoneNumber(phoneNumber);
            repository.save(updateUser);
            return true;
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthorities(authorities);
        user.setPhoneNumber(phoneNumber);
        user.setUsername(String.valueOf(randomUsername));
        repository.save(user);
        return true;
    }

    @Override
    public Boolean registerUserWithPhoneNumber(String phoneNumber) {
        VerificationCodeModel verificationCodeModel = new VerificationCodeModel();
        String smsSingleRequestServerUrl = "https://smssh1.253.com/msg/v1/send/json ";
        String verifCode = verificationCode.generateCode();
        String msg = "【点我拿】您的注册验证码是" + verifCode;

        //状态报告
        String report= "true";
        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phoneNumber,report);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        // save the verification code and time into table
        Optional<VerificationCodeModel> message = verificationCodeRepository.findByPhoneNumberIfExists(phoneNumber);
        if(message.isPresent()){
            VerificationCodeModel codeModel = message.get();
            codeModel.setCode(verifCode);
            codeModel.setPhoneNumber(phoneNumber);
            codeModel.setTime(String.valueOf(LocalDateTime.now()));
            verificationCodeRepository.save(codeModel);
            SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
            System.out.println("IM working here !!!!!!");
            return true;
        }
        verificationCodeModel.setCode(verifCode);
        verificationCodeModel.setPhoneNumber(phoneNumber);
        verificationCodeModel.setTime(String.valueOf(LocalDateTime.now()));
        verificationCodeRepository.save(verificationCodeModel);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        return true;
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber) {
        Optional<ApplicationUser> result = repository.findByPhoneNumberIfExists(phoneNumber);
        return result.isPresent();
    }

    @Override
    public Boolean isValidPhoneNumber(String phoneNumber, String regionCode) {
        try {
            Phonenumber.PhoneNumber parsedNumber = new Phonenumber.PhoneNumber();
            parsedNumber.setCountryCode(Integer.parseInt(regionCode));
            parsedNumber.setNationalNumber(Long.parseLong(phoneNumber));
            return false;
        } catch (NumberFormatException e) {
            // Invalid phone number format
            return false;
        }
    }

    // check if the given confirmation code and sent confirmation codes are same
    @Override
    public Boolean isValidConfirmationMessage(String phoneNumber, String code) {

        System.out.println("The code: " + code + "  verificationCode: ");
        System.out.println("The Fucking object: " + verificationCodeRepository.findByPhoneNumberIfExists(phoneNumber));
        Optional<VerificationCodeModel> storedVerificationCode = verificationCodeRepository.findByPhoneNumberIfExists(phoneNumber);

        if(storedVerificationCode.isPresent()){
            VerificationCodeModel verificationCodeModel = storedVerificationCode.get();
            System.out.println("The fucking code taken by query: " + storedVerificationCode.get());
            String savedCode = verificationCodeModel.getCode();
            int comparisonResult = code.compareTo(savedCode);
            System.out.println("The comparision Result: " + comparisonResult);
            return comparisonResult == 0;
        }
        return false;
    }
    @Override
    public Boolean isValidPassword(String password, String confirmationPassword) {
        int comparisonResult = password.compareTo(confirmationPassword);
        return comparisonResult == 0;
    }
}
