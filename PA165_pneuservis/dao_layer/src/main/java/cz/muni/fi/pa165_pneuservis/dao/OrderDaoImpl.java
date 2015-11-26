/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
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
public class OrderDaoImpl implements OrderDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Order order) {
        em.persist(order);
    }
    
    @Override
    public void update(Order order) {
        em.merge(order);
    }
    
    @Override
    public void remove(Order order) {
        em.remove(order);
    }
    
    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }
    
    @Override
    public List<Order> findAll() {
        TypedQuery<Order> que = em.createQuery("SELECT o FROM Order o",Order.class);
        return que.getResultList();
    }
    
    @Override
    public List<Order> findByCustomer(Customer customer){
        TypedQuery<Order> que = em.createQuery(
                "SELECT o FROM Order o WHERE o.customer = :customer",Order.class)
                .setParameter("customer", customer);
        return que.getResultList();
    }
}
