package pl.wimiip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wimiip.repository.CustomerDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishi on 2016-06-04.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> list() {
        Map<String, Object> model = new HashMap<>();
        model.put("customers", customerDAO.getAllCustomers());
        return model;
    }
}
