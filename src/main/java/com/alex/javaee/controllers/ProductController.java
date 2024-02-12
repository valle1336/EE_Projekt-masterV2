package com.alex.javaee.controllers;

import com.alex.javaee.models.user.UserEntity;
import com.alex.javaee.models.user.UserRepository;
import com.alex.javaee.models.user.ads.AdEntity;
import com.alex.javaee.models.user.ads.AdEntityDetailsService;
import com.alex.javaee.models.user.ads.Categories;
import com.alex.javaee.models.user.ads.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    private AdEntityDetailsService adService;


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

    //Edit Ads with dynamic endpoints
    @GetMapping("/editAd")
    public String showEditAdPage(
            @RequestParam("adId")
            Long adId,
            Model model
    ) {
        AdEntity ad = productRepository.findById(adId).orElse(null);

        if (ad == null) {
            return "error.page";
        }
        model.addAttribute("adId", adId);
        model.addAttribute("adTitle", ad.getTitle());
        model.addAttribute("adDescription", ad.getDescription());

        return "editAd";
    }

    @GetMapping("/viewAd/{id}")
    public String getViewAd(@PathVariable("id") Long id, Model model) {
        AdEntity ad = adService.findById(id);

        if (ad == null) {
            return "redirect:/error";
        }
        model.addAttribute("adEntity", ad);
        return "viewAd";
    }

    @GetMapping("/deleteAd")
    public String showDeleteAd(
            @RequestParam("adId")
            Long adId,
            Model model
    ) {
        AdEntity ad = productRepository.findById(adId).orElse(null);

        if (ad == null) {
            return "error.page";
        }
        model.addAttribute("adId", adId);
        model.addAttribute("adTitle", ad.getTitle());
        model.addAttribute("adDescription", ad.getDescription());

        return "deleteAd";
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

    @PostMapping("/save-edit-ad")
    public String saveAd(
            @RequestParam("adId") Long adId,
            @RequestParam("title") String title,
            @RequestParam("description") String description)
    {

        AdEntity ad = productRepository.findById(adId).orElse(null);
        if (ad == null) {
            return "error-page";
        }

        ad.setTitle(title);
        ad.setDescription(description);
        productRepository.save(ad);

        return "redirect:/myAds";
    }

    @DeleteMapping("/delete-Ad/{id}")
    public String deleteEntity(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/myAds";
    }

    @RequestMapping("/viewAd/{id}")
    public String viewAd(@PathVariable("id") Long id, Model model) {
        AdEntity ad = adService.findById(id);
        model.addAttribute("adEntity", ad);

        return "viewAd";
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
