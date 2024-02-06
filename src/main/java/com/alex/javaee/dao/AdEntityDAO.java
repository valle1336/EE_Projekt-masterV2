package com.alex.javaee.dao;

import com.alex.javaee.models.user.ads.AdEntity;
import com.alex.javaee.models.user.ads.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AdEntityDAO implements IAdEntityDAO{


    private ProductRepository productRepository;

    @Autowired
    public AdEntityDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public AdEntity findByTitle(String title) {
        return productRepository.findProductByTitle(title);
    }

    @Override
    public Collection<AdEntity> findAllTitles() {
        return productRepository.findAll();
    }

    @Override
    public void save(AdEntity adEntity) {

    }

    @Override
    public void update() {

    }

    @Override
        public void delete() {

    }
}
