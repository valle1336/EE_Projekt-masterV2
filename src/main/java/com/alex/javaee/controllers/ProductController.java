package com.alex.javaee.controllers;

import com.alex.javaee.models.user.UserEntity;
import com.alex.javaee.models.user.UserRepository;
import com.alex.javaee.models.user.ads.AdEntity;
import com.alex.javaee.models.user.ads.Categories;
import com.alex.javaee.models.user.ads.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/createAd")
    public String createAdPage(AdEntity adEntity, Model model) {
        model.addAttribute("categories", Categories.values());

        return "createAd";
    }

    @GetMapping("/")
    public String showProducts(AdEntity adEntity, Model model)  {
        List<AdEntity> products = productRepository.findAll();
        model.addAttribute("products", products);

        return "home";
    }

    @GetMapping("/myAds")
    public String showMyProducts(AdEntity adEntity, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity currentUser = userRepository.findUserByUsername(username);
        List<AdEntity> userAds = currentUser.getAds();


        model.addAttribute("userAds", userAds);

        return "myAds";
    }


    @PostMapping("/createAd")
    public String createAnAd(
            @Valid AdEntity adEntity,
            BindingResult result,
            Categories categories
    ) {
        if (result.hasErrors()) {
            return "createAd";
        }

        productRepository.save(adEntity);

        return "redirect:/";

        // Fixa postmapping så att vi kan spara våra annonser i databasen!
    }

}
