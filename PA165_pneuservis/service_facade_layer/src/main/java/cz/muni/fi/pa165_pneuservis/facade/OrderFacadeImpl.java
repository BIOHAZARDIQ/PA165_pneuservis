/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderItemDTO;
import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.ServiceService;
import cz.muni.fi.pa165_pneuservis.service.TireService;
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
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private TireService tireService;
    
    @Autowired
    private ServiceService serviceService;
    
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
    public void createOrder(OrderDTO orderDTO) throws PneuFacadeException {
        Order order = new Order();
        try {
            Customer customer = customerService.findCustomerByEmail(
                    orderDTO.getCustomer().getEmail());
            order.setCustomer(customer);
            
            for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                Class orderItemClass = orderItemDTO.getItem().getClass();
                if(orderItemClass == TireDTO.class) {
                    orderItem.setItem(tireService.getTireById(orderItemDTO.getItem().getId()));
                }
                else if(orderItemClass == ServiceDTO.class) {
                    orderItem.setItem(serviceService.findServiceById(orderItemDTO.getItem().getId()));
                }
                order.addOrderItem(orderItem);
            }
            orderService.createOrder(order);
        } catch (PneuBusinessException e) {
            throw new PneuFacadeException("Can't create new Order. Reason: " + e.getMessage(), e);
        }
    }
}
