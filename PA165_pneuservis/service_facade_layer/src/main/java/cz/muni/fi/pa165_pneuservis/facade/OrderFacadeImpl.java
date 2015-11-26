/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public List<OrderDTO> getAllOrders() {
        return beanMappingService.mapTo(orderService.findAllOrders(), OrderDTO.class);
    }
    
    @Override
    public List<OrderDTO> getOrdersByCustomer(Long customerId) {
        return beanMappingService.mapTo(orderService.getOrdersByCustomer(customerId), OrderDTO.class);
    }
    
    @Override
    public OrderDTO getOrderById(Long id) {
        return beanMappingService.mapTo(orderService.getOrderById(id), OrderDTO.class);
    }
    
    @Override
    public void cancelOrder(Long id) {
        orderService.cancelOrder(id);
    }
    
    @Override
    public void createOrder(OrderDTO order) {
        orderService.createOrder(beanMappingService.mapTo(order, Order.class));
    }
}
