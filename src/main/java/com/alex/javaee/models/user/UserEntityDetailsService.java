package com.alex.javaee.models.user;

import com.alex.javaee.dao.UserEntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserEntityDetailsService implements UserDetailsService {
private final UserEntityDAO userEntityDAO;

            @Autowired
            public UserEntityDetailsService(UserEntityDAO userEntityDAO) {
                this.userEntityDAO = userEntityDAO;
            }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityDAO.findByUsername(username);
    }

}
