/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
    public String listTires(Model model) {
        List<TireDTO> tires = tireFacade.findAllTires();
        model.addAttribute("tires", tires);
        return "tire/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTire(Model model) {
        model.addAttribute("tire", new TireDTO());
        return "tire/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("tire") TireDTO tire, BindingResult result, 
            Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                m.addAttribute(fieldError.getField() + "_error", true);
            }
            return "tire/new";
        }
        //create tire
        tireFacade.createTire(tire);
        
        return "redirect:" + uriBuilder.path("/tire/list").toUriString();
    }
}
