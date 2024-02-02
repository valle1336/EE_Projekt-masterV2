package com.alex.javaee.controllers;

import com.alex.javaee.config.PasswordConfig;
import com.alex.javaee.dao.UserEntityDAO;
import com.alex.javaee.models.user.Roles;
import com.alex.javaee.models.user.UserEntity;
import com.alex.javaee.models.user.UserRepository;
import com.alex.javaee.models.user.ads.AdEntity;
import com.alex.javaee.models.user.ads.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final PasswordConfig appPasswordConfig; // Bcrypt
    private final UserEntityDAO userEntityDAO;

    @Autowired
    public UserController(UserRepository userRepository, PasswordConfig appPasswordConfig, UserEntityDAO userEntityDAO) {
        this.userRepository = userRepository;
        this.appPasswordConfig = appPasswordConfig;
        this.userEntityDAO = userEntityDAO;
    }

    @GetMapping("/register")
    public String registerUserPage(UserEntity userEntity, Model model) {

        model.addAttribute("roles", Roles.values());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid UserEntity userEntity,   // Enables Error Messages
            BindingResult result,           // Ties the object with result
            Roles roles
    ) {

        // Check FOR @Valid Errors
        if (result.hasErrors()) {
            return "register";
        }

        // TODO - Optionally: handle duplicate entries (@Unique PREFERABLY within ENTITY)
        userEntity.setPassword(
                appPasswordConfig.bCryptPasswordEncoder().encode(userEntity.getPassword())
        );

        userEntity.setAccountEnabled(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);

        userRepository.save(userEntity);

        return "redirect:/login";
    }
}
