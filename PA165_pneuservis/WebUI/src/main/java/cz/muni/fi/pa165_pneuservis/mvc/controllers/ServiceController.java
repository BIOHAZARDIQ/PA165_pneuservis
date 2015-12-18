/*
* Team project for course PA165 - Enterprise Applications in Java
* For more informations see file README.md
*/
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import java.util.List;
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
@RequestMapping("/service")
public class ServiceController {
    
    @Autowired
    private ServiceFacade serviceFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<ServiceDTO> services = serviceFacade.findAllService();
        model.addAttribute("services", services);
        return "service/list";
    }
}
