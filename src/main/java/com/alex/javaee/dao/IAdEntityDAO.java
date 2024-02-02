package com.alex.javaee.dao;

import com.alex.javaee.models.user.ads.AdEntity;

import java.util.Collection;

public interface IAdEntityDAO {

    AdEntity findByTitle (String title);
    Collection<AdEntity> findAllTitles();
    void save(AdEntity adEntity);
    void update();
    void delete();

}
