/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.model.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

/**
 * Tests for Service data access object implementation
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceDaoImplTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private ServiceDao serviceDao;
    
    @Test
    public void testCreateService() {
        Service service = new Service();
        serviceDao.create(service);
        
        Service found = em.find(Service.class, service.getId());
        assertNotNull(service.getId());
        assertEquals(service.getId(), found.getId());
        
        em.remove(service);
    }
}
