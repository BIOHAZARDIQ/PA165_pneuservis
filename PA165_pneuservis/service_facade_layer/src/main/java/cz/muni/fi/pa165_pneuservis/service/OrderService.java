/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.model.Order;
import java.util.Collection;
import java.util.List;

/**
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
public interface OrderService {
    public void createOrder(Order order);
    public Collection<Order> findPendingOrders();
    public Collection<Order> findPreviousOrders();
    public Collection<Order> findAllOrders();
    public List<Order> getOrdersByCustomer(Long customerId);
    public Order getOrderById(Long id);
    public void cancelOrder(Long id);
}
