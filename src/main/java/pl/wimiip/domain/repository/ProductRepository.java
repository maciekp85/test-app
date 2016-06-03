package pl.wimiip.domain.repository;

import pl.wimiip.domain.Product;

import java.util.List;

/**
 * Created by nishi on 2016-06-02.
 */
public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(String productId);
}
