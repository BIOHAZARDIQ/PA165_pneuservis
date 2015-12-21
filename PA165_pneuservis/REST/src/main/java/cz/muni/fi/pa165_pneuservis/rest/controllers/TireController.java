/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.rest.controllers;

import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@RestController
@RequestMapping("/tires")
public class TireController {
    
    @Autowired
    private TireFacade tireFacade;

    /**
     * Getting all Tires in html format (only id, name, price)
     *
     * @return Html table in String format
     */
    @RequestMapping(value = "/htmllist", method = RequestMethod.GET, headers = "Accept=text/html", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getAllTiresHTML() {
        String retval = "<html><body><table border=1>";
        List<TireDTO> listOfTires = tireFacade.findAllTires();
        if (listOfTires == null) {
            retval+="<tr><td>No Tires in database</td></td>";
        } else {
            retval+="<tr><td style=font-weight:bold>Id</td><td style=font-weight:bold>Name</td><td style=font-weight:bold>Price</td></tr>";

            for (TireDTO tireDTO : listOfTires) {
                retval += "<tr><td>" + tireDTO.getId() + "</td><td>" + tireDTO.getName()+ "</td><td>" + tireDTO.getPrice() + "</td></tr>";
            }
        }
        retval += "</table></body></html>";
        
        return retval;
    }    
    
    /**
     * Get Tire by Id
     * curl -i -X GET http://localhost:8080/pa165/rest/tires/1
     * @param id Id of Tire
     * @return Tire
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TireDTO getTire(@PathVariable("id") long id) {               
        return tireFacade.getTireById(id);
    }
    
    /**
     * Get list of Tires
     * curl -i -X GET http://localhost:8080/pa165/rest/tires
     * @return List of Tires in system
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TireDTO> getTires() {  
        return tireFacade.findAllTires();
    }
    
    /**
     * Delete Tire
     * curl -i -X DELETE http://localhost:8080/pa165/rest/tires/delete/1
     * @param id Id of Tire to be deleted
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTire(@PathVariable("id") long id) {               
        tireFacade.deleteTire(tireFacade.getTireById(id));
    }

    /**
     * Delete Tire
     * curl -i -X GET http://localhost:8080/pa165/rest/tires/delete/1
     * @param id Id of Tire to be deleted
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTireGet(@PathVariable("id") long id) {               
        tireFacade.deleteTire(tireFacade.getTireById(id));
    }
    
    /**
     * Update Tire with given parameters
     * Id of Tire to be updated is from PathVariable, not from TireDTO given
     * curl -X PUT -i -H "Content-Type: application/json" --data '{"name":"NEW NAME"}' http://localhost:8080/pa165/rest/tires/update/1
     * curl -X PUT -i -H "Content-Type: application/json" --data '{"name":"NEW_NAME","witdh":155,"rim":17,"ratio":55,"description":"NEW_DESCRIPTION","brand":"NEW_BRAND","price":199.90}' http://localhost:8080/pa165/rest/tires/update/3
     * @param id Id of Tire to be updated
     * @param tireDto parameters to be updated
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void updateTire(@PathVariable("id") long id, @RequestBody TireDTO tireDto) {
        
        TireDTO updatedTire = tireFacade.getTireById(id);
        
        //if Tire with given ID does not exist, skip updating
        if (updatedTire == null)
            return;
        
        //If brand is filled, update it. Otherwise do not change
        if(tireDto.getBrand() != null) {
            updatedTire.setBrand(tireDto.getBrand());
        }

        //If price is filled, update it. Otherwise do not change
        if(tireDto.getPrice() != null) {
            updatedTire.setPrice(tireDto.getPrice());
        }

        //If name is filled, update it. Otherwise do not change
        if(tireDto.getName() != null) {
            updatedTire.setName(tireDto.getName());
        }

        //If description is filled, update it. Otherwise do not change
        if(tireDto.getDescription() != null) {
            updatedTire.setDescription(tireDto.getDescription());
        }
        
        //If ratio is filled, update it. Otherwise do not change
        if(tireDto.getRatio() != null) {
            updatedTire.setRatio(tireDto.getRatio());
        }
        
        //If rim is filled, update it. Otherwise do not change
        if(tireDto.getRim() != null) {
            updatedTire.setRim(tireDto.getRim());
        }
        
        //If width is filled, update it. Otherwise do not change
        if(tireDto.getWidth() != null) {
            updatedTire.setWidth(tireDto.getWidth());
        }
        
        tireFacade.updateTire(updatedTire);
    }
    
    /**
     * Create Tire
     * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"NAME","witdh":155,"rim":17,"ratio":55,"description":"description","brand":"BRAND","price":199.90}' http://localhost:8080/pa165/rest/tires/create
     * @param tire Tire to be created
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void createTire(@RequestBody TireDTO tire) {
        tireFacade.createTire(tire);
    }
}
