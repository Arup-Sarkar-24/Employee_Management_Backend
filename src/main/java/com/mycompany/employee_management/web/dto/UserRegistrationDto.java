package com.mycompany.employee_management.web.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
