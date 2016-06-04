package pl.wimiip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wimiip.domain.Customer;
import pl.wimiip.domain.repository.CustomerRepository;
import pl.wimiip.service.CustomerService;

import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
