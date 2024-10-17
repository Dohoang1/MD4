package com.productmanagement.service;

import com.productmanagement.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    void addProduct(Product product);
    Product getProductById(int id);
    void updateProduct(Product product);
    void deleteProduct(int id);
    List<Product> searchByName(String name);
}
