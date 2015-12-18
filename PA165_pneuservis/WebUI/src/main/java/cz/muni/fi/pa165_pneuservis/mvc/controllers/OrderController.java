/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.facade.OrderFacade;
import cz.muni.fi.pa165_pneuservis.mvc.servlets.UserLogged;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Komoi
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderFacade orderFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<OrderDTO> orders = orderFacade.getAllOrders();
        List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        for(OrderDTO order : orders){
            customers.add(order.getCustomer());
        }
        
        if(UserLogged.usernameLogged.equals("user")){
            int i = 0;
            Iterator<OrderDTO> orderIterator = orders.iterator();
            for (Iterator<CustomerDTO> iterator = customers.iterator(); iterator.hasNext(); i++) {
                if(orderIterator.hasNext()){
                    OrderDTO order = orderIterator.next();
                }
                CustomerDTO customer = iterator.next();
                if (!customer.getEmail().equals("user@securemail.net")) {
                    orderIterator.remove();
                    iterator.remove();
                } 
            }
        }

        
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        return "order/list";
    }
}