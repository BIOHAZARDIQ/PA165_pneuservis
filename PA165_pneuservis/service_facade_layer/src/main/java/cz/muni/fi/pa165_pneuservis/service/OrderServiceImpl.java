/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderItemDao;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer implementation for Order
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    
    /**
     * Creates new Order
     * @param order
     */
    @Override
    public void createOrder(Order order) {
        order.setCreateDate(Date.from(LocalDate.now().atTime(LocalTime.now())
                .toInstant(ZoneOffset.UTC)));
        order.setCompleteDate(null);
        BigDecimal totalPrice = calculateTotalPrice(order.getOrderItems());
        order.setTotalPrice(totalPrice);
        orderDao.create(order);
    }
    
    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }
    
    /**
     * Obtains pending Orders
     * @return
     */
    @Override
    public Collection<Order> findPendingOrders() {
        Collection<Order> pendingOrders = new ArrayList<Order>();
        for(Order order : findAllOrders())
        {
            if(order.getCompleteDate() == null)
            {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    /**
     * * Obtains previous (completed, canceled) Orders
     * @return
     */
    @Override
    public Collection<Order> findPreviousOrders() {
        Collection<Order> previousOrders = new ArrayList<Order>();
        for(Order order : findAllOrders())
        {
            if(order.getCompleteDate() != null)
            {
                previousOrders.add(order);
            }
        }
        return previousOrders;
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
    
    private BigDecimal calculateTotalPrice(Collection<OrderItem> orderItems)
    {
        BigDecimal itemPrice = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(OrderItem orderItem : orderItems)
        {
            itemPrice = orderItem.getItem().getPrice();
            itemPrice = itemPrice.multiply(new BigDecimal(orderItem.getAmount()));
            totalPrice = totalPrice.add(itemPrice);
        }
        return totalPrice;
    }

}