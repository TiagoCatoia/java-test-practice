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

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    @MethodSource("provideValidProducts")
    @DisplayName("Should return true when the product is successfully updated")
    void shouldReturnTrueWhenTheProductIsSuccessfullyUpdated(Product product) {
        Product product1 = new Product("1", "Product A", 10, 100);
        Product product2 = new Product("2", "Product B", 20, 200);
        Product product3 = new Product("3", "Product C", 30, 300);
        when(productRepositoryMock.getProduct(product.getId()))
                .thenReturn(Optional.of(product1))
                .thenReturn(Optional.of(product2))
                .thenReturn(Optional.of(product3));
        when(productRepositoryMock.update(product))
                .thenReturn(Optional.of(product));
        assertThat(sut.updateProduct(product)).isTrue();
    }

    public static Stream<Arguments> provideValidProducts() {
        return Stream.of(
                Arguments.of(new Product("1", "Product A", 50, 10)),
                Arguments.of(new Product("2", "Product B+", 20, 150)),
                Arguments.of(new Product("3", "C Product", 1, 0))
        );
    }
}