/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
import java.util.List;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public interface OrderDao {
    
    public void create(Order order);
    public void update(Order order);
    public void remove(Order order);
    public Order findById(Long id);
    public List<Order> findAll();
    public List<Order> findByCustomer(Customer customer);
}
