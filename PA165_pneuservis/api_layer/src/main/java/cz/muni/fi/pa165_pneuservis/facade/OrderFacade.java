package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import java.util.List;

/**
 *
 * @author Jakub Holy
 */

public interface OrderFacade {
    
        public List<OrderDTO> getAllOrders();
	public List<OrderDTO> getOrdersByCustomer(Long customerId);	
	public OrderDTO getOrderById(Long id);
        public void createOrder(OrderDTO order);
        public void cancelOrder(Long id);  

}
