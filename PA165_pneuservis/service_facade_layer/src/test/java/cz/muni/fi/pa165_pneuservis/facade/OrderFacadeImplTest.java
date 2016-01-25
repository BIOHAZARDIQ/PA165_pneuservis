/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.ServiceService;
import cz.muni.fi.pa165_pneuservis.service.TireService;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Order facade layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class OrderFacadeImplTest {
    
    @Mock
    private OrderService orderServiceMock;
    
    @Mock
    private CustomerService customerServiceMock;
    
    @Mock
    private TireService tireServiceMock;
    
    @Mock
    private ServiceService serviceServiceMock;
    
    @Mock
    private BeanMappingService beanMappingServiceMock;
    
    @InjectMocks
    private final OrderFacade facade = new OrderFacadeImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateOrder() throws PneuFacadeException, PneuBusinessException {        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(new CustomerDTO());
                
        Order order = new Order();
        order.setCustomer(new Customer());
        
        doReturn(order).when(beanMappingServiceMock).mapTo(Matchers.anyObject(),
                (Class<?>)Matchers.any(Class.class));
        
        doReturn(null).when(customerServiceMock).findCustomerByEmail(Matchers.anyString());
        doReturn(null).when(tireServiceMock).getTireById(Matchers.anyLong());
        doReturn(null).when(serviceServiceMock).findServiceById(Matchers.anyLong());
        
        facade.createOrder(orderDTO);  
        verify(orderServiceMock).createOrder((Order) Matchers.anyObject());
        verifyNoMoreInteractions(orderServiceMock);
    }    
}
