/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Jakub Holy
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderDaoImplTest extends AbstractTestNGSpringContextTests{
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private OrderDao orderDao;
    
    private Order order1,order2;
    private Customer customer;
    
    @BeforeMethod
    public void setUp(){
        order1 = new Order();
        Calendar c = Calendar.getInstance();
        order1.setCreateDate(c.getTime());
        customer = new Customer();
        order1.setCustomer(customer);
        order1.setTotalPrice(BigDecimal.TEN);
        
        order2 = new Order();
        order2.setCreateDate(c.getTime());
        order2.setTotalPrice(BigDecimal.ONE);
        order2.setCustomer(customer);
    }
    
    @Test
    public void testCreation(){
        orderDao.create(order1);
        Order order = orderDao.findById(order1.getId());
        Assert.assertEquals(order1, order);
    }
    
    @Test
    public void testRemove(){
        orderDao.create(order1);
        orderDao.create(order2);
        orderDao.remove(order1);
        Assert.assertNull(orderDao.findById(order1.getId()));
    }
    
    @Test
    public void testFindAll(){
        orderDao.create(order1);
        orderDao.create(order2);
        List<Order> orders = orderDao.findAll();
        Assert.assertEquals(orders.size(), 2);
    }
    
    @Test
    public void testFindByCustomer(){
        orderDao.create(order1);
        orderDao.create(order2);
        List<Order> orders = orderDao.findByCustomer(customer);
        Assert.assertEquals(orders.size(), 2);
    }
}
