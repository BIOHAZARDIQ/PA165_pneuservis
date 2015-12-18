/*
* Team project for course PA165 - Enterprise Applications in Java
* For more informations see file README.md
*/
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import cz.muni.fi.pa165_pneuservis.dto.ServiceType;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    
    @Autowired
    private ServiceFacade serviceFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listServices(Model model) {
        List<ServiceDTO> services = serviceFacade.findAllService();
        model.addAttribute("services", services);
        return "service/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newService(Model model) {
        model.addAttribute("service", new ServiceDTO());                
        model.addAttribute("serviceTypes",ServiceType.values());
        return "service/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("service") ServiceDTO service, BindingResult result,
            Model m, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                m.addAttribute(fieldError.getField() + "_error", true);
            }
            return "service/new";
        }
        
        serviceFacade.createService(service);
        redirectAttributes.addFlashAttribute("alert_success", "Service was successfully created");
        return "redirect:" + uriBuilder.path("/service/list").toUriString();
    }
}
