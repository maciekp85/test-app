package pl.wimiip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wimiip.model.Customer;
import pl.wimiip.repository.CustomerDAO;
import pl.wimiip.service.CustomerManage;

import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
@Service
public class CustomerManageService implements CustomerManage {

    @Autowired
    CustomerDAO customerDAO;

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
