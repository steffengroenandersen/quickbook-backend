package com.quickbook.quickbookbackend.dto;

import com.quickbook.quickbookbackend.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    public static User getUserEntity(UserRequest userRequest) {
        return new User(userRequest.username,
                userRequest.email,
                userRequest.password,
                userRequest.firstName,
                userRequest.lastName);
    }
}
