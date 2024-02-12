package com.alex.javaee.config;

import com.alex.javaee.models.user.ads.Image;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration  // Allows Spring to find this config
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/admin-page").setViewName("admin-page");
        registry.addViewController("/createAd").setViewName("createAd");
        registry.addViewController("/myAds").setViewName("myAds");
        registry.addViewController("/viewAd").setViewName("viewAd");

    }
    //Unblocks CSS, JS, Images etc...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**", "/static/**").
                addResourceLocations("/resources/", "classpath:/static/");
    }

        };



