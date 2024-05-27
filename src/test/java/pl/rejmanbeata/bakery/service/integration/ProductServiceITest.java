package pl.rejmanbeata.bakery.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rejmanbeata.bakery.database.ProductEntity;
import pl.rejmanbeata.bakery.jpa_repository.ProductRepository;
import pl.rejmanbeata.bakery.service.ProductService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createProductEntity;

@SpringBootTest
class ProductServiceITest {

    public static final String CAKE = "Cake";
    public static final double PRICE = 17.6;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindProductById_shouldReturnProduct() {
        ProductEntity foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
    }

    @Test
    void testFindProductById_shouldReturnNull() {
        ProductEntity foundProduct = productService.getProductById(8L);

        assertNull(foundProduct);
    }

    @Test
    void testGetAllProducts_shouldFindAll() {
        List<ProductEntity> allProducts = productService.getAllProducts();

        assertNotNull(allProducts);
        assertEquals(2, allProducts.size());
    }

    @Test
    void testSave_shouldSaveProduct() {
        ProductEntity productEntity = createProductEntity(CAKE, PRICE);

        List<ProductEntity> allProducts = productService.getAllProducts();
        assertNotNull(allProducts);

        productRepository.save(productEntity);

        List<ProductEntity> allProductsAfterSave = productService.getAllProducts();

        assertThat(allProductsAfterSave).hasSize(allProducts.size() + 1);
    }

    @Test
    void testDeleteById_shouldDeleteProduct() {
        ProductEntity productEntity = createProductEntity(CAKE, PRICE);
        productService.save(productEntity);
        List<ProductEntity> allProducts = productService.getAllProducts();
        assertNotNull(allProducts);

        productService.deleteProductById(productEntity.getId());

        List<ProductEntity> allProductsAfterDelete = productService.getAllProducts();

        assertThat(allProductsAfterDelete).hasSize(allProducts.size() - 1);
        assertNull(productService.getProductById(productEntity.getId()));
    }
}
