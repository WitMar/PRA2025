package com.pracownia.spring.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataSet {

    List<Seller> sellers;
    List<Product> products;

    public DataSet() {
        sellers = new ArrayList<>();
        products = new ArrayList<>();
    }

}
