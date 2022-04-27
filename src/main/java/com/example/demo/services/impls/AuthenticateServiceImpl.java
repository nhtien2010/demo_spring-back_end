package com.example.demo.services.impls;

import com.example.demo.common.MessageFormatter;
import com.example.demo.domains.UserModel;
import com.example.demo.dtos.requests.LoginRequestDto;
import com.example.demo.dtos.requests.RegisterAdminRequestDto;
import com.example.demo.dtos.requests.RegisterRequestDto;
import com.example.demo.dtos.requests.RegisterUserRequestDto;
import com.example.demo.dtos.responses.LoginResponseDto;
import com.example.demo.dtos.responses.UserResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.GlobalExceptionHandler;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AuthenticateRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AuthenticateService;
import com.example.demo.services.UserService;
import com.example.demo.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

    //private final AuthenticateRepository authenticateRepository;
    //private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public LoginResponseDto authenticate(LoginRequestDto request) {
        if(request.username == null || request.password == null){
            throw new BadRequestException("Invalid request input!");
        }
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.username, request.password));
//
//        }catch (BadCredentialsException e){
//            logger.error("Bad credential exception : {}", e.getMessage());
//            throw new BadRequestException("Wrong username or password!");
//        }

        UserModel userModel = (UserModel) userService.loadUserByUsername(request.username);
        if((passwordEncoder.matches(request.password, userModel.getPassword()))){
            String accessToken = jwtUtil.generateToken(userModel);
            String refreshToken = jwtUtil.generateRefreshToken(userModel);
            UserResponseDto userDto = mapper.map(userModel, UserResponseDto.class);
            return new LoginResponseDto(
                    accessToken, refreshToken, userDto);
        }else {
            throw new BadRequestException("Wrong username or password!");
        }
    }

    @Override
    public Boolean register(RegisterRequestDto dto) {
        boolean isExisted = userService.isUserExist(dto.getUsername());
        if(isExisted){
            throw new BadRequestException(MessageFormatter.formatUserAlreadyExist(dto.getUsername()));
        }else {
            userService.createUser(dto);
        }
        return true;
    }


    @Override
    public Boolean logOut(Long userId) {
        return true;
    }
}
