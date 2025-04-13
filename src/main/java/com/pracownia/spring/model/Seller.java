package com.pracownia.spring.model;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private int id;

    private String name;

    private String city;

    private List<String> products = new ArrayList<>();

    private List<Product> productsOb;

    public List<Product> getProductsOb() {
        return productsOb;
    }

    public void setProductsOb(List<Product> productsOb) {
        this.productsOb = productsOb;
    }

    //required by Hibernate
    public Seller() {

    }

    public Seller(String name, String city, List<String> products) {
        this.name = name;
        this.city = city;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}