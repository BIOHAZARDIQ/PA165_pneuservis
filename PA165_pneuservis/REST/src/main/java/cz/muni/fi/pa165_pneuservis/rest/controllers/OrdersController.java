/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.rest.controllers;

import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.facade.OrderFacade;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    
    @Autowired
    private OrderFacade orderFacade;

    /**
     * Get list of Orders
     * curl -i -X GET http://localhost:8080/pa165/rest/orders
     * @return List of Orders in system
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<OrderDTO> getOrders() {  
        return orderFacade.getAllOrders();
    }
}
