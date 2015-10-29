/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import java.util.List;

/**
 *
 * @author Jakub Holy
 */
public interface OrderItemDao {
    
    public void create(OrderItem orderItem);
    public OrderItem update(OrderItem orderItem);
    public void remove(OrderItem orderItem);
    public OrderItem findById(Long id);
    public List<OrderItem> findAll();
}
