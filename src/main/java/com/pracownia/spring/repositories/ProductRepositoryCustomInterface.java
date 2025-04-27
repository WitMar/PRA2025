package com.pracownia.spring.repositories;


import com.pracownia.spring.model.Product;

import java.util.List;

public interface ProductRepositoryCustomInterface {

    List<Product> listAffordableProducts(Integer price);
}
