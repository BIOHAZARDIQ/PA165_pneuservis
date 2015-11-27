/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Order facade layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class OrderFacadeImplTest {
    
    @Mock
    private OrderService orderServiceMock;
    
    @InjectMocks
    private final OrderFacade facade = new OrderFacadeImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateOrder() {        
        OrderDTO dto = new OrderDTO();
        dto.setId(1L);
        
        Order order = new Order();
        order.setId(1L);
        
        // doReturn(dto).when(customerServiceMock).createCustomer(customer);
        facade.createOrder(dto);
        
        verify(orderServiceMock).createOrder(order);
        verifyNoMoreInteractions(orderServiceMock);
    }

    @Test
    public void testCancelOrder() {
        // TODO
    }
    
}
