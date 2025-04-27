package com.pracownia.spring.repositories;


import com.pracownia.spring.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

    List<Product> findByName(String name);

    @Query("select p from Product p join p.sellers s where s.id = ?1")
    List<Product> findBySellerId(Integer id);

}