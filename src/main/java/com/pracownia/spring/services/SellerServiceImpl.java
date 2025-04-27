package com.pracownia.spring.services;

import com.pracownia.spring.model.Seller;
import com.pracownia.spring.model.Product;
import com.pracownia.spring.repositories.ProductRepository;
import com.pracownia.spring.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository data;

    @Autowired
    ProductRepository dataPr;

    @Autowired
    ProductService productService;

    @Override
    public Iterable<Seller> listAllSellers() {
        return data.findAll();
    }

    @Override
    public Optional<Seller> getSellerById(Integer id) {
        return data.findById(id);
    }

    @Override
    public Seller saveSeller(Seller seller) {
        data.save(seller);
        return seller;
    }

    @Override
    public void deleteSeller(Integer id) {
        data.deleteById(id);
    }

    @Override
    public List<Seller> getByName(String name) {
        return data.findByName(name);
    }

    @Override
    public Integer getNumberOfProducts(Integer id) {
        return  data.countProductsObById(id);
    }

    @Override
    public Optional<Seller> getBestSeller() {
        double max = 0;
        int maxId = 0;
        Iterable<Seller> sellers = data.findAll();
        for(Seller s : sellers) {
            double sum = 0.0;
            List<Product> products = dataPr.findBySellerId(s.getId());
            for(Product pid : products) {
                sum += pid.getPrice().doubleValue();
            }
            if (sum > max) {
                max = sum;
                maxId = s.getId();
            }
        }
        return data.findById(maxId);
    }
}
