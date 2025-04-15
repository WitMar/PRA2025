package com.pracownia.spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Max;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Product entity.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "refId", scope = Product.class)
public class Product {

    private Integer id;

    private String productId;

    private String name;

    @Max(value = 100, message = "Price has to be at mst 100")
    private BigDecimal price;

    private DateTime bestBeforeDate;

    private Set<Seller> sellers = new HashSet<>();

    //required by Hibernate
    public Product() {

    }

    public Product(String productId, String name, BigDecimal price, DateTime date) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.bestBeforeDate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DateTime getBestBeforeDate() {
        return bestBeforeDate;
    }

    public void setBestBeforeDate(DateTime bestBeforeDate) {
        this.bestBeforeDate = bestBeforeDate;
    }

    public Set<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(Set<Seller> sellers) {
        this.sellers = sellers;
    }
}
