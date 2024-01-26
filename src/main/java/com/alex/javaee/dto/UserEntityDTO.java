package com.alex.javaee.dto;

import com.alex.javaee.models.user.Roles;
import com.alex.javaee.models.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEntityDTO {

    private final String username;
    private final String password;
    private final Roles role;

    public UserEntityDTO(UserEntity entity){
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
}
