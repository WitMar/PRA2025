package com.pracownia.spring.controllers;

import com.pracownia.spring.model.Product;
import com.pracownia.spring.model.Seller;
import com.pracownia.spring.services.ProductService;
import com.pracownia.spring.services.SellerService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Homepage controller.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    SellerService sellerService;

    @Autowired
    ProductService productService;

    @GetMapping(value = "")
    String index() {
        return "index";
    }


    @PostMapping(value = "generateModel", produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel() {

        DateTime dateAndTime  = DateTime.now();

        Product p0 = new Product(UUID.randomUUID().toString(),"Chleb", new BigDecimal(3.50), dateAndTime.plusDays(7));
        Product p1 = new Product(UUID.randomUUID().toString(),"Jajko", new BigDecimal(2.50), dateAndTime.plusDays(7));
        Product p2 = new Product(UUID.randomUUID().toString(),"Masło", new BigDecimal(3.50), dateAndTime.plusDays(7));
        Product p3 = new Product(UUID.randomUUID().toString(),"Mąka", new BigDecimal(1.50), dateAndTime.plusDays(7));


        Seller seller = new Seller("Biedra", "Poznan", Arrays.asList(p1.getProductId(), p2.getProductId(), p3.getProductId()));
        Seller seller2 = new Seller("Lidl", "Krosno", Arrays.asList(p1.getProductId(), p2.getProductId()));

        p1.getSellers().add(seller);
        p2.getSellers().add(seller);
        p3.getSellers().add(seller);
        p1.getSellers().add(seller2);
        p2.getSellers().add(seller2);

        productService.saveProduct(p0);
        productService.saveProduct(p1);
        productService.saveProduct(p2);
        productService.saveProduct(p3);

        Seller seller3 = new Seller("Kaufland", "Poznan", new ArrayList<>());
        sellerService.saveSeller(seller3);

        return "Model Generated";
    }
}