package pl.wimiip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wimiip.domain.Product;
import pl.wimiip.domain.repository.ProductRepository;
import pl.wimiip.service.ProductService;

import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }
}
