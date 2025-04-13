package com.pracownia.spring.services;

import com.pracownia.spring.model.DataSet;
import com.pracownia.spring.model.Product;
import com.pracownia.spring.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Product service implement.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private DataSet data;

    @Override
    public Iterable<Product> listAllProducts() {
        return data.getProducts();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return data.getProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(new Random().nextInt());
        data.getProducts().add(product);
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        data.getProducts().removeIf(p -> p.getId().equals(id));
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (data.getProducts().stream().anyMatch(p -> p.getId().equals(id)))
            return true;
        else
            return false;
    }

    @Override
    public List<Product> getProductBySellerId(int id) {
        ArrayList<Product> products = new ArrayList<Product>();
        Optional<Seller> seller = data.getSellers().stream().filter(s -> s.getId() == id).findFirst();
        if(seller.isPresent()) {
             products.addAll(data.getProducts().stream().filter(p -> p.getSellers().contains(seller.get().getId())).collect(Collectors.toList()));
        }
        return products;
    }


}
