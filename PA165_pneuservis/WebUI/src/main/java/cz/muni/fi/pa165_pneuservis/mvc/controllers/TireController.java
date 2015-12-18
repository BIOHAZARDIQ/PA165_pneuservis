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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Tire MVC Controller
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
    public String newTire(@Valid @ModelAttribute("tire") TireDTO tire, BindingResult result, 
            Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                m.addAttribute(fieldError.getField() + "_error", true);
            }
            return "tire/new";
        }
        //create tire
        tireFacade.createTire(tire);
        redirectAttributes.addFlashAttribute("alert_success", "Tire was successfully created");
        return "redirect:" + uriBuilder.path("/tire/list").toUriString();
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTire(Model m, @PathVariable("id") long id) {
        TireDTO tireToBeEdited = tireFacade.getTireById(id);
        m.addAttribute("tire", tireToBeEdited);
        return "tire/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editTire(@Valid @ModelAttribute("tire") TireDTO tire, BindingResult result, 
            Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                m.addAttribute(fieldError.getField() + "_error", true);
            }
            return "tire/edit";
        }
        //edit tire
        tireFacade.updateTire(tire);
        redirectAttributes.addFlashAttribute("alert_success", "Tire " + 
                tire.getBrand() + " " + tire.getName() + "was successfully updated");
        return "redirect:" + uriBuilder.path("/tire/list").toUriString();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTire(@PathVariable("id") long id, 
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //delete tire
        TireDTO tireToBeDeleted = tireFacade.getTireById(id);
        tireFacade.deleteTire(tireToBeDeleted);
        redirectAttributes.addFlashAttribute("alert_success", "Tire " + 
                tireToBeDeleted.getBrand() + " " + tireToBeDeleted.getName() + 
                " was successfully deleted");
        return "redirect:" + uriBuilder.path("/tire/list").toUriString();
    }
}
