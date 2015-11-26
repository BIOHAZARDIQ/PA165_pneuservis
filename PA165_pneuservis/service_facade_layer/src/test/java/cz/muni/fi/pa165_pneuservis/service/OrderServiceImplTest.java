package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderItemDao;
import cz.muni.fi.pa165_pneuservis.model.Order;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private OrderItemDao orderItemDaoMock;
    
    @Mock
    private CustomerDao customerDaoMock;
    
    @Mock
    private OrderDao orderDaoMock;
    
    @Autowired
    @InjectMocks 
    private OrderService orderServiceImpl;
    
    @BeforeClass
    public void setUpClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * Test of createOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setId(1L);
               
        orderServiceImpl.createOrder(order);
        
        verify(orderDaoMock).create(order);
        verifyNoMoreInteractions(orderDaoMock);
    }
}
