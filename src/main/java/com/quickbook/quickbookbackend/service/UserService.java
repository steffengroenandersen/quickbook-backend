package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.UserRequest;
import com.quickbook.quickbookbackend.dto.UserResponse;
import com.quickbook.quickbookbackend.entity.User;
import com.quickbook.quickbookbackend.repository.UserRepository;
import com.quickbook.security.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponse> response = users.stream().map((user -> new UserResponse(user))).toList();
        return response;
    }
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserRequest.getUserEntity(userRequest);

        userRepository.findById(user.getUsername()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        });

        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        user.addRole(Role.USER);
        userRepository.save(user);
        return new UserResponse(user);
    }

    public Page<UserResponse> getAllUsersPageable(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        System.out.println("users: " + users);

        List<UserResponse> userResponses = users.stream().map(user -> new UserResponse(user)).toList();
        System.out.println("userResponses: " + userResponses);

        return new PageImpl<>(userResponses, pageable, users.getTotalElements());
    }
}