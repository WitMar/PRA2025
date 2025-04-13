package com.pracownia.spring.services;

import com.pracownia.spring.model.Product;

import java.util.Optional;
import java.util.List;

public interface ProductService {

    Iterable<Product> listAllProducts();

    Optional<Product> getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);

    Boolean checkIfExist(Integer id);

    List<Product> getProductBySellerId(int id);
}
