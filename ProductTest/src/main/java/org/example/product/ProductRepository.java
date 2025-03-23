package org.example.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    void add(Product product);
    Optional<Product> getProduct(UUID id);
    Optional<Product> update(Product product);
    void remove(Product product);
    List<Product> getAll();
}
