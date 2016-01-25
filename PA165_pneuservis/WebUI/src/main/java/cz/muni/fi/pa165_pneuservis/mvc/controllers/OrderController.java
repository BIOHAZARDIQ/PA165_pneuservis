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
import cz.muni.fi.pa165_pneuservis.facade.PneuFacadeException;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String listOrders(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        CustomerDTO user = (CustomerDTO) session.getAttribute("auth");
        if(user.getIsAdmin()) {
            m.addAttribute("pendingOrders", orderFacade.getPendingOrders());
            m.addAttribute("previousOrders", orderFacade.getPreviousOrders());    
        }
        else{
            m.addAttribute("pendingOrders", orderFacade.getOrdersByCustomer(user.getId()));
        }
        
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
        } catch (PneuFacadeException ex) {
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