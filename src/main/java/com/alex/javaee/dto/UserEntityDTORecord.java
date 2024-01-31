package com.alex.javaee.dto;

import com.alex.javaee.models.user.Roles;
import com.alex.javaee.models.user.UserEntity;

public record UserEntityDTORecord(String username, String password, Roles role) {
    public static UserEntityDTORecord convertEntityToDTO(UserEntity userEntity) {

        return new UserEntityDTORecord(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole());
    }
}
