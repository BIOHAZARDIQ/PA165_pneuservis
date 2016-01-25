/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderItemDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderFormDTO;
import cz.muni.fi.pa165_pneuservis.facade.CustomerFacade;
import cz.muni.fi.pa165_pneuservis.facade.OrderFacade;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderFacade orderFacade;
    
    @Autowired
    private ServiceFacade serviceFacade;
    
    @Autowired
    private TireFacade tireFacade;
    
    @Autowired
    private CustomerFacade customerFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<OrderDTO> orders = orderFacade.getAllOrders();
        List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        for(OrderDTO order : orders){
            customers.add(order.getCustomer());
        }
        
        /*
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
        */
        
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        return "order/list";
    }
    
    /**
     * Action to process submit order form
     * @param orderForm
     * @param m
     * @param result
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitOrder(@Valid @ModelAttribute("orderForm") OrderFormDTO orderForm, 
            Model m, BindingResult result, HttpServletRequest request,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
               
        List<Long> serviceIds = orderForm.getServiceIds();
        List<Long> tireIds = orderForm.getTireIds();
        OrderDTO order = new OrderDTO();
        
        Collection<OrderItemDTO> orderItemCollection = new ArrayList<OrderItemDTO>();
                
        for (Long tireId : tireIds) {
            OrderItemDTO tireItem = new OrderItemDTO();
            tireItem.setItem(tireFacade.getTireById(tireId));
            orderItemCollection.add(tireItem);
        }
        
        for (Long serviceId : serviceIds) {
            OrderItemDTO serviceItem = new OrderItemDTO();
            serviceItem.setItem(serviceFacade.findServiceById(serviceId));
            orderItemCollection.add(serviceItem);
        }
        
        CustomerDTO customer = (CustomerDTO) request.getSession().getAttribute("auth");
        if(customer != null) {
            order.setCustomer(customer);
        }
        
        order.setOrderItems(orderItemCollection);
        
        try {
            orderFacade.createOrder(order);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName())
                    .log(Level.SEVERE,ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Error occured "
                    + "while submitting the order. Please, try again.");
            return "redirect:/";
        }
        
        redirectAttributes.addFlashAttribute("alert_success", "Order was successfully submitted");
        return "redirect:/";
    }
}