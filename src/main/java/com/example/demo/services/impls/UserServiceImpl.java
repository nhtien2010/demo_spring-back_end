package com.example.demo.services.impls;

import com.example.demo.common.MessageFormatter;
import com.example.demo.common.UserRoleEnum;
import com.example.demo.domains.UserModel;
import com.example.demo.domains.UserRole;
import com.example.demo.dtos.requests.RegisterAdminRequestDto;
import com.example.demo.dtos.requests.RegisterRequestDto;
import com.example.demo.dtos.requests.RegisterUserRequestDto;
import com.example.demo.dtos.requests.UpdateUserRequestDto;
import com.example.demo.dtos.responses.UserResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ConflictRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Sets;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;

    private UserModel getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormatter.formatUserNotFound(userId)));
    }
    public Boolean isUserExist(String username){
        return userRepository.findByUsername(username).isPresent();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException(
                                MessageFormatter.formatUserNotFound(username)));
    }


    @Override
    public Boolean lockUser(Long userId) {
        UserModel userModel = getUserById(userId);
        userModel.setIsLocked(true);
        userModel.setUpdatedDate(Date.from(Instant.now()));
        userRepository.save(userModel);
        return true;
    }

    @Override
    public Boolean unlockUser(Long userId) {
        UserModel userModel = getUserById(userId);
        userModel.setIsLocked(false);
        userModel.setUpdatedDate(Date.from(Instant.now()));
        userRepository.save(userModel);
        return true;
    }



    @Override
    public UserResponseDto getUser(Long userId) {
        UserModel userModel = getUserById(userId);
        return mapper.map(userModel, UserResponseDto.class);
    }
    @Override
    public List<UserResponseDto> getUsers() {
        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(userModel -> mapper.map(userModel, UserResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserResponseDto createUser(RegisterRequestDto request) {
        boolean isExisted = isUserExist(request.getUsername());
        if(isExisted){
            throw new ConflictRequestException(MessageFormatter.formatUserAlreadyExist(request.getUsername()));
        }
        UserModel userModel = mapper.map(request, UserModel.class);
        userModel.setIsLocked(false);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(new HashSet<>(Collections.singletonList(new UserRole(UserRoleEnum.CUSTOMER.name()))));
        if(request instanceof RegisterAdminRequestDto){
            String role = ((RegisterAdminRequestDto) request).userRole;
            if(!role.equals(UserRoleEnum.ADMIN.name()) || !userModel.addRole(new UserRole(role)))
            {
                throw new BadRequestException(MessageFormatter.formatInvalidRequestInput(role));
            }
        }
        userModel.setCreatedDate(Date.from(Instant.now()));
        userModel.setUpdatedDate(Date.from(Instant.now()));
        userRepository.save(userModel);
        return mapper.map(userModel, UserResponseDto.class);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public UserResponseDto updateUser(UpdateUserRequestDto userRequest) {
        getUserById(userRequest.getId());
        UserModel update = mapper.map(userRequest, UserModel.class);
        update.setUpdatedDate(Date.from(Instant.now()));
        userRepository.save(update);
        return mapper.map(update, UserResponseDto.class);
    }
}
