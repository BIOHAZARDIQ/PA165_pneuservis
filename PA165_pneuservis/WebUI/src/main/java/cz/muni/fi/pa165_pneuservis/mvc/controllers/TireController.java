/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.enums.TireSort;
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
 * Tire controller
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Controller
@RequestMapping("/tire")
public class TireController {
    
    @Autowired
    private TireFacade tireFacade;
    
    /**
     * Action to show all tires
     * @param m
     * @param sortBy
     * @param asc
     * @return View with list of tires
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listTires(Model m, String sortBy, String asc) {
        if(sortBy == null) {
            m.addAttribute("tires", tireFacade.findAllTires());
            return "tire/list";
        }
        TireSort sort = TireSort.valueOf(sortBy.toUpperCase());
        List<TireDTO> tires = null;
        if(asc == null) // descending
        {
            tires = tireFacade.findAllTiresSorted(sort,false);
        }
        else
        {
            tires = tireFacade.findAllTiresSorted(sort,true);
            m.addAttribute("asc", true);
        }        
        m.addAttribute("sortBy", sortBy);
        m.addAttribute("tires", tires);
        return "tire/list";
    }
    
    /**
     * Action to show new tire form
     * @param model
     * @return View with new tire form
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTire(Model model) {
        model.addAttribute("tire", new TireDTO());
        return "tire/new";
    }
    
    /**
     * Action to process new tire form
     * @param tire
     * @param result
     * @param m
     * @param redirectAttributes
     * @param uriBuilder
     * @return View with list of tires with success message if validation passes, otherwise validation errors
     */
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
    
    /**
     * Action to show edit tire form
     * @param m
     * @param id Id of tire to be edited
     * @return View with edit tire form with pre-filled values
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTire(Model m, @PathVariable("id") long id) {
        TireDTO tireToBeEdited = tireFacade.getTireById(id);
        m.addAttribute("tire", tireToBeEdited);
        return "tire/edit";
    }
    
    /**
     * Action to process edit tire form
     * @param tire
     * @param result
     * @param m
     * @param redirectAttributes
     * @param uriBuilder
     * @return View with list of tires with success message if validation passes, otherwise validation errors
     */
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
                tire.getBrand() + " " + tire.getName() + " was successfully updated");
        return "redirect:" + uriBuilder.path("/tire/list").toUriString();
    }
    
    /**
     * Action to process delete tire form
     * @param id Id of tire to be deleted
     * @param redirectAttributes
     * @param uriBuilder
     * @return View with list of tires
     */
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
