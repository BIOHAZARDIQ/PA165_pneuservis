package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderItemDao;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.List;

import static cz.muni.fi.pa165_pneuservis.service.helper.ServiceTestHelper.toList;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Order service layer tests
 * @author Jozef.Sumaj
 */
public class OrderServiceImplTest {
    
    @Mock
    private OrderItemDao orderItemDaoMock;
    
    @Mock
    private CustomerDao customerDaoMock;
    
    @Mock
    private OrderDao orderDaoMock;
    
    @InjectMocks
    private OrderService service = new OrderServiceImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        // ORDERS
        Order o1 = new Order();
        o1.setId(1L);
        
        Order o2 = new Order();
        o2.setId(2L);
        
        Order o3 = new Order();
        o3.setId(3L);
        
        Order o4 = new Order();
        o3.setId(4L);
        
        doReturn(o1).when(orderDaoMock).findById(1L);
        doReturn(o2).when(orderDaoMock).findById(2L);
        doReturn(o3).when(orderDaoMock).findById(3L);
        doReturn(o4).when(orderDaoMock).findById(4L);
        doReturn(toList(new Order[]{o1,o2,o3,o4})).when(orderDaoMock).findAll();
        
        // CUSTOMERS
        Customer c1 = new Customer();
        c1.setId(1L);
        o1.setCustomer(c1);
        
        Customer c2 = new Customer();
        c2.setId(2L);
        o2.setCustomer(c2);
        
        Customer c3 = new Customer();
        c3.setId(3L);
        o3.setCustomer(c3);
        o4.setCustomer(c3);
        
        doReturn(c1).when(customerDaoMock).findById(1L);
        doReturn(c2).when(customerDaoMock).findById(2L);
        doReturn(c3).when(customerDaoMock).findById(3L);
        doReturn(toList(new Order[]{o3,o4})).when(orderDaoMock).findByCustomer(c3);
        doReturn(toList(new Customer[]{c1,c2,c3})).when(customerDaoMock).findAll();
    }
    
    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setId(1L);

        service.createOrder(order);       
        
        verify(orderDaoMock).create(order);
        verifyNoMoreInteractions(orderDaoMock);
    }
    
    @Test
    public void testUpdateOrder() {
        /*
        Order order = new Order();
        order.setId(1L);

        service.updateOrder(order);

        verify(orderDaoMock).update(order);
        verifyNoMoreInteractions(orderDaoMock);
        */
    }
    
    @Test
    public void testFindAllOrders() {
        List<Order> orders = service.findAllOrders();
        Assert.assertEquals(4, orders.size());

        verify(orderDaoMock).findAll();
        verifyNoMoreInteractions(orderDaoMock);
    }
    
    @Test
    public void testFindById() {
        Order order = service.getOrderById(1L);
        verify(orderDaoMock).findById(1L);
        verifyNoMoreInteractions(orderDaoMock);
    }
    
    @Test
    public void testFindByCustomer() {
        List<Order> orders = service.getOrdersByCustomer(3L);
        verify(customerDaoMock).findById(3L);
        verify(orderDaoMock).findByCustomer(Matchers.any(Customer.class));
        verifyNoMoreInteractions(orderDaoMock);
        verifyNoMoreInteractions(customerDaoMock);
        Assert.assertEquals(2, orders.size());
    }
    
    @Test
    public void testCancelOrder() {   
        Order order = new Order();
        order.setId(1L);
        doReturn(order).when(orderDaoMock).findById(1L);
        
        service.cancelOrder(1L);
        verify(orderDaoMock).findById(1L);
        verify(orderDaoMock).remove(order);
        verifyNoMoreInteractions(orderDaoMock);
    }
    
    @Test
    public void testCancelOrderInvalid() {
        doThrow(new IllegalArgumentException()).when(orderDaoMock).remove(null);
        try{
            service.cancelOrder(null);
            assert(false);
        }catch(Exception e){
            //pass
        }
    }
}
