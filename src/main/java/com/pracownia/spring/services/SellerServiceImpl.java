package com.pracownia.spring.services;

import com.pracownia.spring.model.DataSet;
import com.pracownia.spring.model.Seller;
import com.pracownia.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    DataSet data;

    @Autowired
    ProductService productService;

    @Override
    public Iterable<Seller> listAllSellers() {
        return data.getSellers();
    }

    @Override
    public Optional<Seller> getSellerById(Integer id) {
        return data.getSellers().stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public Seller saveSeller(Seller seller) {
        seller.setId(new Random().nextInt());
        data.getSellers().add(seller);
        return seller;
    }

    @Override
    public void deleteSeller(Integer id) {
        data.getSellers().removeIf(s -> s.getId() == id);
    }

    @Override
    public List<Seller> getByName(String name) {
        return data.getSellers().stream().filter(s -> s.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfProducts(Integer id) {
        return data.getSellers().stream()
                .filter(s -> s.getId() == id)
                .map(s2 -> s2.getProductsOb().size())
                .reduce((a,b) -> a+b).orElse(0);
    }

    @Override
    public Optional<Seller> getBestSeller() {
        double max = 0;
        int maxId = 0;
        Iterable<Seller> sellers = data.getSellers();
        for(Seller s : sellers) {
            double sum = 0.0;
            List<Product> products = productService.getProductBySellerId(s.getId());
            for(Product pid : products) {
                sum += pid.getPrice().doubleValue();
            }
            if (sum > max) {
                max = sum;
                maxId = s.getId();
            }
        }
        return getSellerById(maxId);
    }

}
