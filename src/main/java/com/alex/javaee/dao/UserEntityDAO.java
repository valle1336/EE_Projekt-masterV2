package com.alex.javaee.dao;

import com.alex.javaee.models.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserEntityDAO implements IUserEntityDAO {


    @Override
    public UserEntity findByUsername(String username) {
        return null;
    }

    @Override
    public Collection<UserEntity> findAllUsers() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

}
