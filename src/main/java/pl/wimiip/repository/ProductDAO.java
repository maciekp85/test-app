package pl.wimiip.repository;

import pl.wimiip.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nishi on 2016-06-02.
 */
public interface ProductDAO {

    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getProductsByCategory(String category);
}
