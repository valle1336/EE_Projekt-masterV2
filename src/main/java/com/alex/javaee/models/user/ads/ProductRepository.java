package com.alex.javaee.models.user.ads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<AdEntity, Long> {

    AdEntity findProductByTitle(String title);

}
