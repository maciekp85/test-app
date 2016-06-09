package pl.wimiip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wimiip.model.Product;
import pl.wimiip.repository.ProductDAO;
import pl.wimiip.service.Order;

/**
 * Created by nishi on 2016-06-03.
 */
@Service
public class OrderService implements Order {

    @Autowired
    private ProductDAO productRepository;

    public void processOrder(String productId, int count) {
        Product productById = productRepository.getProductById(productId);
        if(productById.getUnitsInStock() < count){
            throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie: "+ productById.getUnitsInStock());
        }
        productById.setUnitsInStock(productById.getUnitsInStock() - count);
    }
}
