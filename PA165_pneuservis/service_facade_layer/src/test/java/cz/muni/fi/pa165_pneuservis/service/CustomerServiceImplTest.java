/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.Collection;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import static cz.muni.fi.pa165_pneuservis.service.helper.ServiceTestHelper.toList;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Customer service layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class CustomerServiceImplTest {
        
    @Mock
    private CustomerDao customerDaoMock;
    
    @Mock
    private OrderDao orderDaoMock;
    
    @InjectMocks
    private final CustomerService service = new CustomerServiceImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateCustomer() {
        Customer cust = new Customer();
        cust.setId(1L);

        service.createCustomer(cust);        
        verify(customerDaoMock).create(cust);
        verifyNoMoreInteractions(customerDaoMock);
    }
    
    @Test
    public void testDeleteCustomer() {
        Customer c1 = new Customer();
        c1.setId(1L);
        doReturn(c1).when(customerDaoMock).findById(1L);

        service.deleteCustomer(1L);
        verify(customerDaoMock).findById(1L);
        verify(customerDaoMock).remove(c1);
        verifyNoMoreInteractions(customerDaoMock);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteCustomerInvalid() {
        doThrow(new IllegalArgumentException()).when(customerDaoMock).remove(null);
        service.deleteCustomer(null);
        assert(false);
    }
    
    @Test
    public void testFindAllCustomers() {
        Customer c1 = new Customer();
        c1.setId(1L);
        Customer c2 = new Customer();
        c2.setId(2L);
        doReturn(toList(new Customer[]{c1,c2})).when(customerDaoMock).findAll();
        
        Collection<Customer> customers = service.findAllCustomers();
        verify(customerDaoMock).findAll();
        verifyNoMoreInteractions(orderDaoMock);
        Assert.assertEquals(2, customers.size());
    }
    
    @Test
    public void testFindCustomerById() {
        Customer c1 = new Customer();
        c1.setId(1L);
        doReturn(c1).when(customerDaoMock).findById(1L);
        
        Customer customer = service.findCustomerById(1L);
        verify(customerDaoMock).findById(1L);
        verifyNoMoreInteractions(customerDaoMock);
        Assert.assertEquals(Long.valueOf(1), customer.getId());
    }
    
    @Test
    public void testFindCustomerByEmail() throws PneuBusinessException {
        Customer c1 = new Customer();
        String email = "customer@email.qa";
        c1.setEmail(email);
        doReturn(c1).when(customerDaoMock).findByEmail(email);
        
        Customer customer = service.findCustomerByEmail(email);
        verify(customerDaoMock).findByEmail(email);
        verifyNoMoreInteractions(customerDaoMock);
        Assert.assertEquals(email, customer.getEmail());
   }
}
