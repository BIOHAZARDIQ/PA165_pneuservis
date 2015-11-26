package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Implementing Service data access object interface
 * 
 * @author Jozef Sumaj
 */
@Repository
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Service service) {
        if (service == null) {
            throw new DataAccessException("service cannot be null") {};
        }
        em.persist(service);
    }

    @Override
    public void update(Service service) {
        if (service == null) {
            throw new DataAccessException("service cannot be null") {};
        }

        if (em.find(Service.class, service.getId()) == null) {
            throw new DataAccessException("service is not in database") {};
        }
        em.merge(service);
    }

    @Override
    public void remove(Service service) {
        em.remove(service);
    }

    @Override
    public Service findById(Long id) {
        return em.find(Service.class, id);
    }

    @Override
    public List<Service> findAll() {
        TypedQuery<Service> que = em.createQuery("SELECT s FROM Service s", 
                Service.class);
        return que.getResultList();
    }
    
}
