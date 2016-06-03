package pl.wimiip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.service.OrderService;

/**
 * Created by nishi on 2016-06-03.
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/P1234/2")
    public void process() {
        orderService.processOrder("P1234", 2);
    }
}
