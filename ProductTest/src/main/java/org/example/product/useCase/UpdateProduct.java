package org.example.product.useCase;

import org.example.product.Product;
import org.example.product.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class UpdateProduct {
    private final ProductRepository repository;

    public UpdateProduct(ProductRepository repository) {
        this.repository = Objects.requireNonNull(repository, "Repository cannot be null.");
    }

    public boolean updateProduct(Product product) {
        validateProduct(product);

        Optional<Product> existingProduct = repository.getProduct(product.getId());
        if (existingProduct.isEmpty()) {
            throw new NoSuchElementException("Product not found. ID: " + product.getId());
        }

        return repository.update(product).isPresent();
    }

    private void validateProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null.");

        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }

        if (isNullOrEmpty(product.getName())) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }

        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative. Given: " + product.getQuantity());
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    String.format("Product price must be greater than 0. Given: %.2f", product.getPrice())
            );
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
