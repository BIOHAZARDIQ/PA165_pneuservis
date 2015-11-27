/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Customer facade layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class CustomerFacadeImplTest {

    @Mock
    private CustomerService customerServiceMock;
    
    @InjectMocks
    private final CustomerFacade facade = new CustomerFacadeImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateCustomer() {        
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        
        Customer customer = new Customer();
        customer.setId(1L);
        
        // doReturn(dto).when(customerServiceMock).createCustomer(customer);
        facade.createCustomer(dto);
        
        verify(customerServiceMock).createCustomer(customer);
        verifyNoMoreInteractions(customerServiceMock);
    }
       
    @Test
    public void testFindAllCustomers() {
        // TODO
    }
    
    @Test
    public void testFindCustomerById() {
        // TODO
    }
    
    @Test
    public void testFindCustomerByEmail() {
        // TODO
    }
    
    @Test
    public void testDeleteCustomer() {
        // TODO
    }

}
