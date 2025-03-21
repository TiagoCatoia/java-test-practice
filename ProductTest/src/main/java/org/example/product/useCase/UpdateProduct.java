package org.example.product.useCase;

import org.example.product.Product;
import org.example.product.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class UpdateProduct {
    private final ProductRepository repository;

    public UpdateProduct(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean updateProduct(Product product) {
        Product prod = Objects.requireNonNull(product);

        if (prod.getId() == null || prod.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }

        if (repository.getProduct(prod.getId()).isEmpty())
            throw new NoSuchElementException("Product not exists. id: " + prod.getId());

        if (prod.getName() == null || prod.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }

        if (prod.getQuantity() < 0 || prod.getPrice() <= 0) {
            throw new IllegalArgumentException("Quantity and Price cannot be negative and price cannot be 0. quantity: "
                    + prod.getQuantity()
                    + " price: " + prod.getPrice());
        }
        Optional<Product> prodUpdated = repository.update(prod);
        return prodUpdated.isPresent();
    }
}
