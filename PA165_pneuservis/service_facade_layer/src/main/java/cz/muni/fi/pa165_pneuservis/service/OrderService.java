/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.model.Order;
import java.util.List;

/**
 *
 * @author Komoi
 */
public interface OrderService {
    void createOrder(Order order);
    List<Order> findAllOrders();
    public List<Order> getOrdersByCustomer(Long customerId);
    public Order getOrderById(Long id);
    public void cancelOrder(Long id);
    
    //and many more...
}
