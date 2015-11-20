/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderItemDao;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Komoi
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    
    
    @Override
    public void createOrder(Order order) {

     for (OrderItem orderItem : order.getOrderItems()) {
            orderItemDao.create(orderItem);
        }
        orderDao.create(order);
    }
    
    @Override
    public List<Order> findAllOrders() {
            return orderDao.findAll();
    }

    @Override
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderDao.findByCustomer(customerDao.findById(customerId));
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public void cancelOrder(Long id) {
        orderDao.remove(orderDao.findById(id));
    }
    


    
    
}