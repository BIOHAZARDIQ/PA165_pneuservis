package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Implementing Tire data access object interface
 * 
 * @author Jozef.Sumaj
 */
public class TireDaoImpl implements TireDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("tire cannot be null");
        }
        em.persist(tire);
    }

    @Override
    public void update(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("tire cannot be null");
        }

        if (em.find(Customer.class, tire.getId()) == null) {
            throw new IllegalArgumentException("tire is not in database");
        }
        em.merge(tire);
    }

    @Override
    public void remove(Tire tire) {
        em.remove(tire);
    }

    @Override
    public Tire findById(Long id) {
        return em.find(Tire.class, id);
    }

    @Override
    public List<Tire> findAll() {
        TypedQuery<Tire> que = em.createQuery("SELECT t FROM Tire t", Tire.class);
        return que.getResultList();
    }

}
