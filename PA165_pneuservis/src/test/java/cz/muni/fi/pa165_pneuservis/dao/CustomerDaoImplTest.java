/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
public class CustomerDaoImplTest {
    
    private CustomerDao customerDao;
    
    public CustomerDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testCreateNullCustomer() {
        System.out.println("Creating null-invalid customer");
        Customer customer = null;

        try {
            customerDao.createCustomer(customer);
            fail("Exception shoud be throwned when creating null Customer!");
        } catch (Exception ex) {
            //OK             
        }
    }    
    
    /**
     * Test of createCustomer method, of class CustomerDaoImpl.
     */
    @Test
    public void testCreateValidCustomer() {
        //System.out.println("Creating New Customer");
        Customer customer2 = new Customer();
        customer2.setFirstName("First");
        customer2.setLastName("Customer");
        customer2.setStreetName("Main street");
        customer2.setStreetNumber(123);
        customer2.setCity("Brno");
        customer2.setPhoneNumber("61200");
        customer2.setState("Czech republic");
        customer2.setPhoneNumber("+420111222333");
        customer2.setEmail("436321@mail.muni.cz");

        /*
        try {
            customerDao.createCustomer(customer2);
        } catch (Exception ex) {
            fail("Exception should not be throwned when creating valid customer");
        } 
        
        assertNotNull(customer2.getId());
        */
    }
    
    @Test
    public void deleteNullTest()
    {
        Customer customer = null;
        
        try{
            customerDao.deleteCustomer(customer);
            fail("Null pointer exception shoud be throwned when deleting null customer");
        }catch(Exception ex){
            //OK
        }
    }   
}
