package pl.wimiip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wimiip.model.Product;
import pl.wimiip.repository.ProductRepository;
import pl.wimiip.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
