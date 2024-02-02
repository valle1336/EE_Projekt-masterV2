package com.alex.javaee.dao;

import com.alex.javaee.models.user.UserEntity;
import com.alex.javaee.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserEntityDAO implements IUserEntityDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserEntityDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Collection<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

}
