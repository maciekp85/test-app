package pl.wimiip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.domain.repository.CustomerRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishi on 2016-06-04.
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/customers")
    public Map<String, Object> list() {
        Map<String, Object> model = new HashMap<>();
        model.put("customers", customerRepository.getAllCustomers());
        return model;
    }
}
