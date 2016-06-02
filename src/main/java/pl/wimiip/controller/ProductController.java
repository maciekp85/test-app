package pl.wimiip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.domain.Product;
import pl.wimiip.domain.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishi on 2016-05-27.
 */
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/products")
    public Map<String, Object> list() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productRepository.getAllProducts());
        return model;
    }
}
