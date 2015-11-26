/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
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
import org.springframework.dao.DataAccessException;
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
    private TireDao tireDao;
    
    @Test
    public void testCreateTire() {
        Tire tire = new Tire();
        tireDao.create(tire);
        
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
        tireDao.update(tire);
        
        Tire updatedTire = em.find(Tire.class, tire.getId());
        assertEquals(newBrand, updatedTire.getBrand());
        
        em.remove(tire);
    }
    
    @Test
    public void testRemoveTire() {
        Tire tire = new Tire();
        em.persist(tire);
        
        Long tireId = tire.getId();
        tireDao.remove(tire);
        
        Tire tireFromDB = em.find(Tire.class, tireId);
        assertNull(tireFromDB);
    }
    
    @Test
    public void testFindAllTires() {
        List<Tire> tires = new LinkedList<>();
        Tire tire = new Tire();
        
        tire.setName("Nokian A195");
        tires.add(tire);
        em.persist(tire);
        
        Tire tire2 = new Tire();
        tire2.setName("Nokian A215");
        tires.add(tire2);
        em.persist(tire2);
        
        List<Tire> tiresFromDB = tireDao.findAll();
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
        Tire tireFromDB = tireDao.findById(tireId);
        
        assertEquals(description, tireFromDB.getDescription());
        
        em.remove(tire);
    }
    
    @Test
    public void testGetTireIdInvalid() {
        try {
            tireDao.findById(null);
            fail("DataAccessException should be raised - finding on null tire");
        } catch ( DataAccessException ex ) {
            //OK
        }
        
        try {
            tireDao.findById(Long.MAX_VALUE);
            fail("DataAccessException should be raised - finding on nonexisting tire");
        } catch ( DataAccessException ex ) {
            //OK
        }
    }
}