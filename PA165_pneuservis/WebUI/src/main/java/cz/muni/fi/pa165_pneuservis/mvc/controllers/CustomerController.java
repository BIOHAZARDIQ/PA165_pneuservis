/*
* Team project for course PA165 - Enterprise Applications in Java
* For more informations see file README.md
*/
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.facade.CustomerFacade;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerFacade customerFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<CustomerDTO> customers = customerFacade.findAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/list";
    }
}
