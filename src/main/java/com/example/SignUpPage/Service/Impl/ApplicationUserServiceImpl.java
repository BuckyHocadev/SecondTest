package com.example.SignUpPage.Service.Impl;
import com.example.SignUpPage.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final ApplicationRepository applicationRepository;

    public ApplicationUserServiceImpl(BCryptPasswordEncoder passwordEncoder, ApplicationRepository applicationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("The FUCKING username: " + username);
//        if(isPhoneNumber(username)){
//            System.out.println("IT is fucking by phonenumbrer: " + applicationRepository.findByPhoneNumberIfExists(username));
//
//            return applicationRepository.findByPhoneNumberIfExists(username).orElseThrow(()-> new UsernameNotFoundException("user not found by phone number "));
//        }
        System.out.println("IT is fucking by username: " + applicationRepository.findByPhoneNumberIfExists(username));
        return applicationRepository.findByPhoneNumberIfExists(username).orElseThrow(()-> new UsernameNotFoundException("user not found by phone number "));
//        return applicationRepository.findByUsernameIfExists(username).orElseThrow(()-> new UsernameNotFoundException("user not found by username"));
    }

    private boolean isPhoneNumber(String identifier) {
        System.out.println("It is fucking here also: " + applicationRepository.checkByPhoneNumberIfExists(identifier));
        return applicationRepository.checkByPhoneNumberIfExists(identifier);

    }
}
