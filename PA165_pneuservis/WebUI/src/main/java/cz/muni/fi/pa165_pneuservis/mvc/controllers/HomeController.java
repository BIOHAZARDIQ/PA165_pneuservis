/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.OrderItemDTO;
import cz.muni.fi.pa165_pneuservis.dto.OrderFormDTO;
import cz.muni.fi.pa165_pneuservis.enums.TireSort;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home MVC Controller
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
