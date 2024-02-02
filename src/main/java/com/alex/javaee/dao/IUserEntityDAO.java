package com.alex.javaee.dao;

import com.alex.javaee.models.user.UserEntity;

import java.util.Collection;

public interface IUserEntityDAO {

   UserEntity findByUsername (String username);
   Collection<UserEntity> findAllUsers();
   void save(UserEntity userEntity);
   void update();
   void delete();

}
