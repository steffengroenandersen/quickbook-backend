package com.quickbook.quickbookbackend.config.api;

import com.quickbook.quickbookbackend.dto.UserRequest;
import com.quickbook.quickbookbackend.dto.UserResponse;
import com.quickbook.quickbookbackend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ANONYMOUS ENDPOINTS
    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        System.out.println("createUser");
        System.out.println(userRequest);
        return userService.createUser(userRequest);
    }

    // ADMIN ENDPOINTS
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/pageable")
    public Page<UserResponse> getAllUsersPageable(Pageable pageable) {
        return userService.getAllUsersPageable(pageable);
    }
}
