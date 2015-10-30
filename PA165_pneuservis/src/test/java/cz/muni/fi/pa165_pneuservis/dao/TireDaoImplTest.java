package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

/**
 *
 * @author Jozef.Sumaj
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TireDaoImplTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private TireDao tireDaoImpl;

    @Test
    public void testCreateTire()
    {
        Tire tire = new Tire();
        tireDaoImpl.create(tire);
        
        Tire found = em.find(Tire.class, tire.getId());
        assertNotNull(tire.getId());
        assertEquals(tire.getId(), found.getId());
        
        em.remove(tire);
    }
    
    @Test
    public void testUpdateTire()
    {
        Tire tire = new Tire();
        em.persist(tire);
        
        String newBrand = "Barum"; 
        tire.setBrand(newBrand);
        tireDaoImpl.update(tire);
        
        Tire updatedTire = em.find(Tire.class, tire.getId());
        assertEquals(newBrand, updatedTire.getBrand());
        
        em.remove(tire);
    }
    
    @Test
    public void testRemoveTire()
    {
        Tire tire = new Tire();
        em.persist(tire);

        Long tireId = tire.getId();
        tireDaoImpl.remove(tire);

        Tire goalFromDB = em.find(Tire.class, tireId);
        assertNull(goalFromDB);
    }
    
}
