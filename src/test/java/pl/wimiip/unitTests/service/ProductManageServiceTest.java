package pl.wimiip.unitTests.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.wimiip.model.Customer;
import pl.wimiip.repository.impl.CustomerRepository;
import pl.wimiip.service.CustomerManage;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Created by nishi on 2016-06-09.
 */
public class ProductManageServiceTest {

    private ClassPathXmlApplicationContext context;
    private CustomerManage customerManage;
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("META-INF/spring/testapp-context.xml");
        customerManage = context.getBean(CustomerManage.class);
        customerRepository = context.getBean(CustomerRepository.class);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customerList = customerManage.getAllCustomers();
        assertNotNull(customerList);
        assertTrue(customerList.size() == 3);
    }

    @Test
    public void testCheckIfTester() {
        List<Customer> customerList = customerManage.getAllCustomers();
        Customer tester = customerRepository.getTester();
        assertNotNull(customerList);
        assertNotNull(tester);
        assertEquals(tester.getCustomerId(), customerList.get(0).getCustomerId());
        assertEquals(tester.getName(), customerList.get(0).getName());
        assertEquals(tester.getAddress(), customerList.get(0).getAddress());
    }
}
