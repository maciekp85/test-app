package pl.wimiip.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishi on 2016-05-27.
 */
@RestController
public class ProductController {

    @RequestMapping("/products")
    public Map<String, Object> list() {
        Map<String, Object> model = new HashMap<>();
        Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
        iphone.setDescription("Apple iPhone 5s, smartfon z 4-calowym ekranem o rozdzielczości 640x1136 oraz 8-megapikselowym aparatem");
        iphone.setCategory("Smart Phone");
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);
        model.put("product", iphone);
        return model;
    }
}
