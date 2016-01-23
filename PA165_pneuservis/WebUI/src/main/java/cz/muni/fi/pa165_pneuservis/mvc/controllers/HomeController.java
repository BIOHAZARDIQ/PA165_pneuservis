/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.OrderFormDTO;
import cz.muni.fi.pa165_pneuservis.enums.TireSort;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home controller
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private ServiceFacade serviceFacade;
    
    @Autowired
    private TireFacade tireFacade;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("orderForm", new OrderFormDTO());
        model.addAttribute("services", serviceFacade.findAllService());
        model.addAttribute("tires", tireFacade.findAllTiresSorted(TireSort.NAME, true));
        return "index";
    }
}
