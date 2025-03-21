package org.example.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void add(Product product);
    Optional<Product> getProduct(String id);
    Optional<Product> update(Product product);
    void remove(Product product);
    List<Product> getAll();
}
