package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.testng.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

/**
 * Tests for Tire data access object implementation
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
    public void testCreateTire() {
        Tire tire = new Tire();
        tireDaoImpl.create(tire);
        
        Tire found = em.find(Tire.class, tire.getId());
        assertNotNull(tire.getId());
        assertEquals(tire.getId(), found.getId());
        
        em.remove(tire);
    }
    
    @Test
    public void testUpdateTire() {
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
    public void testRemoveTire() {
        Tire tire = new Tire();
        em.persist(tire);

        Long tireId = tire.getId();
        tireDaoImpl.remove(tire);

        Tire tireFromDB = em.find(Tire.class, tireId);
        assertNull(tireFromDB);
    }
    
    @Test
    public void testFindAllTires() {
        List<Tire> tires = new LinkedList<Tire>();
        Tire tire = new Tire();

        tire.setName("Nokian A195");
        tires.add(tire);
        em.persist(tire);

        Tire tire2 = new Tire();
        tire2.setName("Nokian A215");
        tires.add(tire2);
        em.persist(tire2);

        List<Tire> tiresFromDB = tireDaoImpl.findAll();
        assertEquals(tires, tiresFromDB);
        
        em.remove(tire);
        em.remove(tire2);
    }
    
    @Test
    public void testGetTireById() {
        Tire tire = new Tire();
        String description = "Well known for durability and low noise level.";
        tire.setDescription(description);

        em.persist(tire);
        
        Long tireId = tire.getId();
        Tire tireFromDB = tireDaoImpl.findById(tireId);
        
        assertEquals(description, tireFromDB.getDescription());
        
        em.remove(tire);
    }
    
    @Test
    public void testGetTireIdInvalid() {
        try {
            tireDaoImpl.findById(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        if(tireDaoImpl.findById(Long.MAX_VALUE) != null) {
            fail();
        }
    }
}
