/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.rest.controllers;

import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import cz.muni.fi.pa165_pneuservis.facade.ServiceFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@RestController
@RequestMapping("/services")
public class ServiceController {
    
    @Autowired
    private ServiceFacade serviceFacade;

    /**
     * Get list of Services
     * curl -i -X GET http://localhost:8080/pa165/rest/services
     * @return List of Services in system
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ServiceDTO> getServices() {  
        return serviceFacade.findAllService();
    }
}
