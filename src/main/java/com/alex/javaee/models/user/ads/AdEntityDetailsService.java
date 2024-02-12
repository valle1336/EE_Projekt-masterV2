package com.alex.javaee.models.user.ads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdEntityDetailsService {
    @Autowired
    ProductRepository productRepository;

    public void deleteProduct(Long adId) {
        productRepository.deleteById(adId);
    }

    public AdEntity findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

