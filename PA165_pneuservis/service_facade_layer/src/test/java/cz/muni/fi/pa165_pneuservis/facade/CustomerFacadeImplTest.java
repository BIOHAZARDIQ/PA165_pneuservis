/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import java.util.Collection;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import static cz.muni.fi.pa165_pneuservis.service.helper.ServiceTestHelper.toList;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


/**
 * Customer facade layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class CustomerFacadeImplTest {

    @Mock
    private CustomerService customerServiceMock;
    
    @Mock
    private BeanMappingService beanMappingServiceMock;
    
    @InjectMocks
    private final CustomerFacade facade = new CustomerFacadeImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
          
    @Test
    public void testFindAllCustomersFacade() {
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        CustomerDTO dto1 = new CustomerDTO();
        CustomerDTO dto2 = new CustomerDTO();
        
        doReturn(toList(new Customer[]{c1,c2})).when(customerServiceMock).findAllCustomers();
        doReturn(toList(new CustomerDTO[]{dto1,dto2})).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Customer.class),(Class<?>)Matchers.any(Class.class));
        
        Collection<CustomerDTO> customersDTO = facade.findAllCustomers();
        Assert.assertEquals(customersDTO.size(), 2);
        
        verify(customerServiceMock).findAllCustomers();
        verifyNoMoreInteractions(customerServiceMock);
    }
    
    @Test
    public void testFindCustomerByIdFacade() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        Customer c1 = new Customer();
        c1.setId(1L);
        
        doReturn(c1).when(customerServiceMock).findCustomerById(1L);
        doReturn(dto).when(beanMappingServiceMock).mapTo(Matchers.any(Customer.class),
                (Class<?>)Matchers.any(Class.class));
                
        CustomerDTO customerDTO = facade.findCustomerById(1L);
        Assert.assertEquals(Long.valueOf(1), customerDTO.getId());

        verify(customerServiceMock).findCustomerById(1L);
        verify(beanMappingServiceMock).mapTo(c1, CustomerDTO.class);
        verifyNoMoreInteractions(customerServiceMock);
    }
    
    @Test
    public void testFindCustomerByEmailFacade() {
        String email = "customer@email.qa";
        CustomerDTO dto = new CustomerDTO();
        dto.setEmail(email);
        Customer c1 = new Customer();
        c1.setEmail(email);

        doReturn(c1).when(customerServiceMock).findCustomerByEmail(email);
        doReturn(dto).when(beanMappingServiceMock).mapTo(Matchers.any(Customer.class),
                (Class<?>)Matchers.any(Class.class));

        CustomerDTO customerDTO = facade.findCustomerByEmail(email);
        Assert.assertEquals(email, customerDTO.getEmail());

        verify(customerServiceMock).findCustomerByEmail(email);
        verify(beanMappingServiceMock).mapTo(c1, CustomerDTO.class);
        verifyNoMoreInteractions(customerServiceMock);
    }
    
    @Test
    public void testCreateCustomerFacade() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        Customer c1 = new Customer();
        c1.setId(1L);
        
        doReturn(c1).when(beanMappingServiceMock).mapTo(Matchers.any(CustomerDTO.class),
                (Class<?>)Matchers.any(Class.class));
        
        facade.createCustomer(dto);
        
        verify(customerServiceMock).createCustomer(c1);
        verifyNoMoreInteractions(customerServiceMock);
    }
    
    @Test
    public void testDeleteCustomerFacade() {
        facade.deleteCustomer(1L);        
        verify(customerServiceMock).deleteCustomer(1L);
        verifyNoMoreInteractions(customerServiceMock);
    }

}
