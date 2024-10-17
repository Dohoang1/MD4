package com.productmanagement.service;

import com.productmanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Laptop", 1200.99, "Powerful laptop with 16GB RAM", "Dell"));
        products.put(2, new Product(2, "Smartphone", 699.99, "Latest 5G smartphone", "Samsung"));
        products.put(3, new Product(3, "Headphones", 199.99, "Noise-cancelling headphones", "Sony"));
        products.put(4, new Product(4, "Monitor", 299.99, "27-inch 4K monitor", "LG"));
        products.put(5, new Product(5, "Keyboard", 49.99, "Mechanical gaming keyboard", "Corsair"));
        products.put(6, new Product(6, "Mouse", 29.99, "Wireless mouse with ergonomic design", "Logitech"));
        products.put(7, new Product(7, "Smartwatch", 399.99, "Water-resistant smartwatch with GPS", "Apple"));
        products.put(8, new Product(8, "Tablet", 499.99, "10.1-inch tablet with 64GB storage", "Huawei"));
        products.put(9, new Product(9, "Printer", 149.99, "All-in-one wireless printer", "HP"));
        products.put(10, new Product(10, "Camera", 899.99, "DSLR camera with 24MP sensor", "Canon"));
    }
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    private int getLastId() {
        if (products.isEmpty()) {
            return 0;
        }
        return Collections.max(products.keySet());
    }
    @Override
    public void addProduct(Product product) {
        int newId = getLastId() + 1;
        product.setId(newId);
        products.put(newId, product);
    }

    @Override
    public Product getProductById(int id) {
        return products.get(id);
    }

    @Override
    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void deleteProduct(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return products.values().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
