package pl.wimiip.repository;

import pl.wimiip.model.Customer;

import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
public interface CustomerRepository {

    List<Customer> getAllCustomers();

}
