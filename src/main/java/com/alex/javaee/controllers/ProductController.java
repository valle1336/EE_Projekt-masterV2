package com.alex.javaee.controllers;

import com.alex.javaee.dao.AdEntityDAO;
import com.alex.javaee.dao.IAdEntityDAO;
import com.alex.javaee.models.user.ads.AdEntity;
import com.alex.javaee.models.user.ads.Categories;
import com.alex.javaee.models.user.ads.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
