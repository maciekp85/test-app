package pl.wimiip.service;

import pl.wimiip.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nishi on 2016-06-04.
 */
public interface ProductManage {
    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getProductsByCategory(String category);
}
