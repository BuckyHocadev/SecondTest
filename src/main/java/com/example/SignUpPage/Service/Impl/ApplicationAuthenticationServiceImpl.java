package com.example.SignUpPage.Service.Impl;
import com.example.SignUpPage.Dto.ApplicationLoginResponseDto;
import com.example.SignUpPage.Repository.ApplicationRepository;
import com.example.SignUpPage.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationAuthenticationServiceImpl {

    @Autowired
    private ApplicationRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationTokenServiceImpl tokenService;


    public ApplicationLoginResponseDto loginUser(String username, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new ApplicationLoginResponseDto(token);
        }catch (AuthenticationException e){
            return new ApplicationLoginResponseDto(null, "User not found ");
        }
    }
}
