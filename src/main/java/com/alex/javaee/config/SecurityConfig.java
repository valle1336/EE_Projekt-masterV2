package com.alex.javaee.config;


import com.alex.javaee.models.user.UserEntityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

import static com.alex.javaee.models.user.Roles.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity               // Enables use of @PreAuthorize
public class SecurityConfig {

    // Info about Authentication & Authorities:
    // Authentication - identity (Are you who you say you are?) // I am Batman (Username & Password)
    // Authorities - Role & Permissions
    //      ROLE_ADMIN  == GET, POST, PUT, DELETE
    //      ROLE_BATMAN == GET, POST, PUT
    //      ROLE_USER   == GET, POST
    //      ROLE_GUEST  == GET
    private final PasswordConfig passwordConfig;
    private final UserEntityDetailsService userEntityDetailsService;

    @Autowired
    public SecurityConfig(PasswordConfig passwordConfig, UserEntityDetailsService userEntityDetailsService) {
        this.passwordConfig = passwordConfig;
        this.userEntityDetailsService = userEntityDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/register", "/static/**", "/api/**", "/createAd", "/logout").permitAll()
                        .requestMatchers("/admin-page").hasRole(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login"))   // Override /login
                // .httpBasic()
                .rememberMe(rememberMe ->
                        rememberMe.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(14))
                                .rememberMeParameter("remember-me")
                                .key("someSecureKey")
                                .userDetailsService(userEntityDetailsService)
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/"))
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordConfig.bCryptPasswordEncoder());
        provider.setUserDetailsService(userEntityDetailsService);

        return provider;
    }

}
