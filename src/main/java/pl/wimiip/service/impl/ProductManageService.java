package pl.wimiip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wimiip.model.Product;
import pl.wimiip.repository.ProductDAO;
import pl.wimiip.service.ProductManage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nishi on 2016-06-04.
 */
@Service
public class ProductManageService implements ProductManage {

    @Autowired
    ProductDAO productRepository;

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
