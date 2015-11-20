/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Komoi
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelOrder(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
