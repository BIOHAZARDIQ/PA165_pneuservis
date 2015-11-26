/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Implementing Tire data access object interface
 *
 * @author Jozef.Sumaj
 */
@Repository
public class TireDaoImpl implements TireDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Tire tire) {
        if (tire == null) {
            throw new DataAccessException("tire cannot be null") {};
        }
        em.persist(tire);
    }
    
    @Override
    public void update(Tire tire) {
        if (tire == null) {
            throw new DataAccessException("tire cannot be null") {};
        }
        
        if (em.find(Tire.class, tire.getId()) == null) {
            throw new DataAccessException("tire for update is not in database") {};
        }
        em.merge(tire);
    }
    
    @Override
    public void remove(Tire tire) {
        em.remove(tire);
    }
    
    @Override
    public Tire findById(Long id) {
        if (id == null) {
            throw new DataAccessException("id of tire cannot be null") {};
        }
        
        Tire foundTire = em.find(Tire.class, id);
        if (foundTire == null) {
            throw new DataAccessException("tire to find is not in database") {};
        }
        
        return foundTire;
    }
    
    @Override
    public List<Tire> findAll() {
        TypedQuery<Tire> que = em.createQuery("SELECT t FROM Tire t", Tire.class);
        return que.getResultList();
    }
}
