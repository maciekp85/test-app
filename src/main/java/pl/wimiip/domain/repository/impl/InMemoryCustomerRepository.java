package pl.wimiip.domain.repository.impl;

import org.springframework.stereotype.Repository;
import pl.wimiip.domain.Customer;
import pl.wimiip.domain.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private List<Customer> listofCustomers = new ArrayList<Customer>();

    public InMemoryCustomerRepository() {
        Customer developer = new Customer("C1234", "Mark", "Kazimierza 3");
        Customer tester = new Customer("C1235", "Maciek", "złota 10");
        Customer productOwner = new Customer("C1236", "Karol", "wadowicka 5");
        listofCustomers.add(developer);
        listofCustomers.add(tester);
        listofCustomers.add(productOwner);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return listofCustomers;
    }
}
