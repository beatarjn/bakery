package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.ProductEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createProductEntity;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    private static final String DONUT = "Donut";
    private static final double PRICE = 1.99;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveProduct() {
        ProductEntity product = createProductEntity(DONUT, PRICE);

        ProductEntity savedProduct = productRepository.save(product);

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(DONUT);
        assertThat(savedProduct.getPrice()).isEqualTo(PRICE);
    }

    @Test
    void shouldFindProductById() {
        ProductEntity product = new ProductEntity(DONUT, PRICE);
        ProductEntity savedProduct = productRepository.save(product);

        Optional<ProductEntity> foundProduct = productRepository.findById(savedProduct.getId());

        assertThat(foundProduct)
                .isPresent()
                .containsSame(savedProduct);
    }

    @Test
    void shouldFindAllProducts() {
        List<ProductEntity> allProducts = productRepository.findAll();

        assertNotNull(allProducts);
        assertEquals(2, allProducts.size());
    }

}