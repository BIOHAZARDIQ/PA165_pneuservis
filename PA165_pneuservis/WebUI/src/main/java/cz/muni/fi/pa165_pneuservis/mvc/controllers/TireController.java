/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Controller
@RequestMapping("/tire")
public class TireController {
    
    @Autowired
    private TireFacade tireFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TireDTO> tires = tireFacade.findAllTires();
        model.addAttribute("tires", tires);
        return "tire/list";
    }
}
