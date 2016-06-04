package pl.wimiip.service;

import pl.wimiip.domain.Product;

import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String productId);
}
