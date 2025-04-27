package com.pracownia.spring.repositories;


import com.pracownia.spring.model.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller, Integer> {
    List<Seller> findByName(String name);

    @Query("select count(*) from Seller s join s.productsOb p where s.id = ?1")
    Integer countProductsObById(Integer id);
}