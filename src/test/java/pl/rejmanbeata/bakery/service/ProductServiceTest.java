package pl.rejmanbeata.bakery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.ProductEntity;
import pl.rejmanbeata.bakery.jpa_repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    private ProductEntity product;

    @BeforeEach
    public void setup() {
        product = ProductEntity.builder()
                .id(1L)
                .name("product")
                .price(2)
                .build();
    }

    @Test
    void testSave_shouldSaveAndReturnProduct() {
        given(productRepository.save(product)).willReturn(product);

        ProductEntity savedProduct = productService.save(product);

        assertThat(savedProduct).isNotNull();
    }

    @Test
    void testGetAllProducts_shouldReturnAllProducts() {
        ProductEntity product2 = ProductEntity.builder()
                .id(1L)
                .name("product2")
                .price(23)
                .build();

        given(productRepository.findAll()).willReturn(List.of(product, product2));

        List<ProductEntity> products = productService.getAllProducts();

        assertThat(products)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void testGetAllProducts_shouldReturnEmptyList() {
        given(productRepository.findAll()).willReturn(Collections.emptyList());

        List<ProductEntity> products = productService.getAllProducts();

        assertThat(products).isEmpty();
    }

    @Test
    void testGetProductById_shouldReturnProductById() {
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        ProductEntity savedProduct = productService.getProductById(product.getId());

        assertThat(savedProduct).isNotNull();
    }

    @Test
    void testDelete_shouldDeleteProductById() {
        long productId = 1L;

        willDoNothing().given(productRepository).deleteById(productId);

        productService.deleteProductById(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}