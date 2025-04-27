package com.pracownia.spring.services;

import com.pracownia.spring.model.Product;
import com.pracownia.spring.repositories.ProductRepository;
import com.pracownia.spring.repositories.ProductRepositoryCustom;
import com.pracownia.spring.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Product service implement.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository data;

    @Autowired
    private SellerRepository sellerData;

    @Override
    public Iterable<Product> listAllProducts() {
        return data.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return data.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        data.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        data.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (data.existsById(id))
            return true;
        else
            return false;
    }

    @Override
    public List<Product> getProductBySellerId(int id) {
        return data.findBySellerId(id);
    }

    @Override
    public Iterable<Product> listAllProductsPaging(Integer pageNr, Integer howManyOnPage) {
        return data.findAll(PageRequest.of(pageNr,howManyOnPage));
    }

    @Autowired
    ProductRepositoryCustom productRepositoryWithCQ;

    @Override
    public Iterable<Product> listAllBelowPrice(Integer price) {
        return productRepositoryWithCQ.listAffordableProducts(price);
    }




}
