package com.mycompany.employee_management.services;

import com.mycompany.employee_management.model.User;
import com.mycompany.employee_management.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
