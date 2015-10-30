package cz.muni.fi.pa165_pneuservis.dao;
import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.testng.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Holy
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderItemDaoImplTest extends AbstractTestNGSpringContextTests{
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private OrderItemDao orderItemDao;
    
    private OrderItem orderItem1,orderItem2;
    
    @BeforeMethod
    public void setUp() {
        orderItem1 = new OrderItem();
        orderItem1.setAmount(10);
        Tire t = new Tire();
        t.setBrand("Michelin");
        t.setName("Michelin 140");
        t.setPrice(BigDecimal.TEN);
        t.setRatio(140);
        t.setRim(16);
        t.setWidth(45);
        orderItem1.setItem(t);
        
        orderItem2 = new OrderItem();
        orderItem2.setAmount(11);
        orderItem2.setItem(t);
    }

    @Test
    public void testFindById() {
        orderItemDao.create(orderItem1);
        OrderItem o = orderItemDao.findById(orderItem1.getId());
        Assert.assertEquals(orderItem1, o);
    }
    
    @Test
    public void testFindAll(){
        orderItemDao.create(orderItem1);
        orderItemDao.create(orderItem2);
        List<OrderItem> items = orderItemDao.findAll();
        Assert.assertEquals( 2, items.size());
    }
    
    @Test
    public void testRemove(){
        orderItemDao.create(orderItem1);
        orderItemDao.create(orderItem2);        
        orderItemDao.remove(orderItem1);
        List<OrderItem> items = orderItemDao.findAll();
        Assert.assertEquals( 1, items.size());
    }
    
    @Test
    public void testUpdate(){
        orderItemDao.create(orderItem1);
        orderItemDao.create(orderItem2);
        orderItem1.setAmount(20);
        orderItemDao.update(orderItem1);
        OrderItem o = orderItemDao.findById(orderItem1.getId());
        Assert.assertEquals( 20, (int) o.getAmount());
    }
}

