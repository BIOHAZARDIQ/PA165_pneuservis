/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CustomerDaoImplTest extends AbstractTestNGSpringContextTests{
   
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private CustomerDao customerDao;
    
    private Customer customer1, customer2, customer3; 
    
    public CustomerDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeMethod
    public void setUp() {
        customer1 = new Customer();
        customer2 = new Customer();
        customer3 = new Customer();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCustomersCreation(){
        customerDao.create(customer1);
        customerDao.create(customer2);
        
        Assert.assertNotSame(customer1.getId(), customer2.getId());

        Customer foundCustomer = customerDao.findById(customer1.getId());
        Assert.assertEquals(customer1, foundCustomer);
    }
    
    @Test
    public void testCustomerRemove(){
        customerDao.create(customer1);
        customerDao.create(customer2);
        customerDao.remove(customer1);
        
        try {
            customerDao.findById(customer1.getId());
            fail("DataAccessException should be raised - finding on deleted customer");
        } catch ( DataAccessException ex ) {
            //OK
        }
        
        try {
            customerDao.remove(customer1);
            fail("DataAccessException should be raised - deleting already deleted customer");
        } catch ( DataAccessException ex ) {
            //OK
        }
        
        Assert.assertNotNull(customerDao.findById(customer2.getId()));
    }
    
    @Test
    public void testFindAll() {
        customerDao.create(customer1);
        customerDao.create(customer2);
        customerDao.create(customer3);
        
        List<Customer> allCustomers = customerDao.findAll();
        Assert.assertEquals(allCustomers.size(), 3);
        
        customerDao.remove(customer3);   
        customerDao.remove(customer2);
        allCustomers = customerDao.findAll();
        Assert.assertEquals(allCustomers.size(), 1);
        
        customerDao.remove(customer1);
        allCustomers = customerDao.findAll();
        Assert.assertEquals(allCustomers.size(), 0);
    }
    
    @Test
    public void testRemoveEmCustomer () {
     
        Customer customer = new Customer();
        em.persist(customer);
        
        Long customerId = customer.getId();
        em.remove(customer);

        Customer foundCustomer = em.find(Customer.class, customerId);
        assertNull(foundCustomer);
    }
    
    @Test
    public void testCreateEmCustomer () {
     
        Customer customer = new Customer();
        em.persist(customer);
        
        Customer foundCustomer = em.find(Customer.class, customer.getId());
        assertNotNull(customer.getId());

        assertEquals(customer.getId(), foundCustomer.getId());
        em.remove(customer);
    }
}
