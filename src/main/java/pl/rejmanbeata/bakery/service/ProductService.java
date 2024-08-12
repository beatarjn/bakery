package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.ProductEntity;
import pl.rejmanbeata.bakery.jpa_repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity address) {
        return productRepository.save(address);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
