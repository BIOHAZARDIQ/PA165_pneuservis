/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jakub Holy
 */
@Repository
public class OrderItemDaoImpl implements OrderItemDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(OrderItem orderItem) {
        em.persist(orderItem);
    }
    
    @Override
    public void update(OrderItem orderItem) {
        em.merge(orderItem);
    }
    
    @Override
    public void remove(OrderItem orderItem) {
        em.remove(orderItem);
    }
    
    @Override
    public OrderItem findById(Long id) {
        return em.find(OrderItem.class, id);
    }
    
    @Override
    public List<OrderItem> findAll() {
        TypedQuery<OrderItem> que = em.createQuery("SELECT o FROM OrderItem o",
                OrderItem.class);
        return que.getResultList();
    } 
}
