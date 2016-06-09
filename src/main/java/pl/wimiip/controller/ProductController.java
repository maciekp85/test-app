package pl.wimiip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.service.ProductManage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nishi on 2016-05-27.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductManage productManageService;

    @RequestMapping
    public Map<String, Object> list() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productManageService.getAllProducts());
        return model;
    }

    @RequestMapping("/all")
    public Map<String, Object> allProducts() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productManageService.getAllProducts());
        return model;
    }

    @RequestMapping("/{category}")
    public Map<String, Object> getProductsByCategory(@PathVariable("category") String productCategory) {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productManageService.getProductsByCategory(productCategory));
        return model;
    }

}
