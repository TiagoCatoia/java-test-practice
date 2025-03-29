package org.example.product.useCase;

import org.example.product.Product;
import org.example.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateProductTest {
    @Mock
    ProductRepository productRepositoryMock;
    @InjectMocks
    UpdateProduct sut;

    @Test
    @DisplayName("Should throw NullPointerException when Product is null")
    void shouldThrowNullPointerExceptionWhenProductIsNull() {
        assertThatNullPointerException().isThrownBy(() -> sut.updateProduct(null));
    }

    @ParameterizedTest
    @MethodSource("provideValidProductsChanges")
    @DisplayName("Should return true when the product is successfully updated")
    void shouldReturnTrueWhenTheProductIsSuccessfullyUpdated(Product updatedProduct, UUID expectedId) {
        Product existingProduct = new Product(expectedId, "Old Product", 5, 50);

        when(productRepositoryMock.getProduct(expectedId))
                .thenReturn(Optional.of(existingProduct));

        when(productRepositoryMock.update(updatedProduct))
                .thenReturn(Optional.of(updatedProduct));

        assertThat(sut.updateProduct(updatedProduct)).isTrue();
    }

    public static Stream<Arguments> provideValidProductsChanges() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();

        return Stream.of(
                Arguments.of(new Product(id1, "New Product", 5, 50), id1),
                Arguments.of(new Product(id2, "Old Product", 80, 50), id2),
                Arguments.of(new Product(id3, "Old Product", 5, 0), id3),
                Arguments.of(new Product(id4, "P", 150, 1), id4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidProductsChanges")
    @DisplayName("Should throw IllegalArgumentException for invalid products")
    void shouldThrowExceptionForInvalidProducts(Product product, String message) {
        assertThatThrownBy(() -> sut.updateProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    private static Stream<Arguments> provideInvalidProductsChanges() {
        return Stream.of(
                Arguments.of(new Product(null, "Produto A", 100.0, 10),
                        "Product ID cannot be null."),
                Arguments.of(new Product(UUID.randomUUID(), null, 100.0, 10),
                        "Product name cannot be null or empty."),
                Arguments.of(new Product(UUID.randomUUID(), "", 100.0, 10),
                        "Product name cannot be null or empty."),
                Arguments.of(new Product(UUID.randomUUID(), "Produto C", 100.0, -1),
                        "Product quantity cannot be negative. Given: -1"),
                Arguments.of(new Product(UUID.randomUUID(), "Produto D", 0.0, 10),
                        String.format("Product price must be greater than 0. Given: %.2f", 0.0)),
                Arguments.of(new Product(UUID.randomUUID(), "Produto E", -5.678, 10),
                        String.format("Product price must be greater than 0. Given: %.2f", -5.678))
        );
    }

    @Test
    @DisplayName("Should throw NoSuchElementException when product does not exist")
    void shouldThrowNoSuchElementExceptionWhenProductDoesNotExist() {
        UUID uuid = UUID.randomUUID();
        Product nonexistentProduct = new Product(uuid, "Old Product", 5, 50);

        assertThatThrownBy(() -> sut.updateProduct(nonexistentProduct))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found. ID: " + uuid);
    }

    @ParameterizedTest
    @MethodSource("provideValidProductsChanges")
    @DisplayName("Should return false when the product failed to update")
    void shouldReturnFalseWhenTheProductFailedToUpdate(Product updatedProduct, UUID expectedId) {
        Product existingProduct = new Product(expectedId, "Old Product", 5, 50);

        when(productRepositoryMock.getProduct(expectedId))
                .thenReturn(Optional.of(existingProduct));

        when(productRepositoryMock.update(updatedProduct))
                .thenReturn(Optional.empty());

        assertThat(sut.updateProduct(updatedProduct)).isFalse();
    }
}