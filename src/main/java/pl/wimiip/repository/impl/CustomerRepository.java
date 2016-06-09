package pl.wimiip.repository.impl;

import org.springframework.stereotype.Repository;
import pl.wimiip.model.Customer;
import pl.wimiip.repository.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishi on 2016-06-04.
 */
@Repository
public class CustomerRepository implements CustomerDAO {

    private Customer developer;
    private Customer tester;
    private Customer productOwner;

    public Customer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Customer developer) {
        this.developer = developer;
    }

    public Customer getTester() {
        return tester;
    }

    public void setTester(Customer tester) {
        this.tester = tester;
    }

    public Customer getProducOwner() {
        return productOwner;
    }

    public void setProducOwner(Customer producOwner) {
        this.productOwner = producOwner;
    }

    private List<Customer> listofCustomers = new ArrayList<Customer>();

    public CustomerRepository() {
    }

    @Override
    public List<Customer> getAllCustomers() {
        listofCustomers.add(tester);
        listofCustomers.add(developer);
        listofCustomers.add(productOwner);
        return listofCustomers;
    }
}
