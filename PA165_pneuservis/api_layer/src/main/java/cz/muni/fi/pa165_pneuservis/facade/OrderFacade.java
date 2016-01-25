/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import java.util.Collection;
import java.util.List;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public interface OrderFacade {
    
    public Collection<OrderDTO> getAllOrders();
    public Collection<OrderDTO> getPendingOrders();
    public Collection<OrderDTO> getPreviousOrders();
    public Collection<OrderDTO> getOrdersByCustomer(Long customerId);
    public OrderDTO getOrderById(Long id);
    public void createOrder(OrderDTO order) throws PneuFacadeException;
    public void cancelOrder(Long id);
}
