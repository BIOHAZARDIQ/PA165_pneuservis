/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.rest.controllers;

import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.facade.TireFacade;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@Controller
@RequestMapping("/tires")
public class TireController {
    
    //@Autowired
    private TireFacade tireFacade;
    
    static ArrayList<Tire> Tires;
    static Random randomGenerator;
    static String brand;
    static Integer width;
    static Integer ratio;
    static Integer rim;
    static String description;
    static String name;
    static Integer index;
    static Double priceDouble;
    static BigDecimal priceBigDecimal;
    
    static {
        Tires = new ArrayList<>();
        Tire tire = null;
        
        //List of names that will be randomly selected when creating Tires
        ArrayList<String> tireBrand = new ArrayList<>(Arrays.asList("Michelin","Yokohama","Pirelli","Goodyear","Barum"));
        ArrayList<Integer> tireWidth = new ArrayList<>(Arrays.asList(155,165,175,185,195));
        ArrayList<Integer> tireRatio = new ArrayList<>(Arrays.asList(55,60,65,70,75));
        ArrayList<Integer> tireRim = new ArrayList<>(Arrays.asList(14,15,16,17,18,19));
        randomGenerator = new Random();
        
        //create 20 Tires with random parameters
        for (long i = 1; i <= 20; i++) {
            index = randomGenerator.nextInt(tireBrand.size());
            brand = tireBrand.get(index);
            index = randomGenerator.nextInt(tireWidth.size());
            width = tireWidth.get(index);
            index = randomGenerator.nextInt(tireRatio.size());
            ratio = tireRatio.get(index);
            index = randomGenerator.nextInt(tireRim.size());
            rim = tireRim.get(index);
            
            //Composed tire name and description
            name = brand+"/"+width+"/"+ratio+"/"+rim;
            description = "This is "+brand+" tire with width:"+width+", ratio:"+ratio+" and rim:"+rim;
            
            //Random price from interval
            double max_interval = 600;
            double min_interval = 200;
            priceDouble = Math.random() * (max_interval - min_interval) + min_interval;
            priceBigDecimal = BigDecimal.valueOf(priceDouble).setScale(2, RoundingMode.CEILING);
            
            //create tire with random parameters
            tire = new Tire(i, name, description, brand, width, ratio, rim, priceBigDecimal);
            Tires.add(tire);
        }
    } 
    
    /**
     * Getting all Tires
     *
     * @return ArrayList of Tires
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Tire> getAllTires() {
        return Tires;
    }

    /**
     * Getting all Tires in html format (only id, name, price)
     *
     * @return Html table in String format
     */
    @RequestMapping(value = "/htmllist", method = RequestMethod.GET, headers = "Accept=text/html", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getAllTiresHTML() {
        String retval = "<html><body><table border=1>";
        retval+="<tr><td style=font-weight:bold>Id</td><td style=font-weight:bold>Name</td><td style=font-weight:bold>Price</td></tr>";
	
        for (Tire tire : Tires) {
            retval += "<tr><td>" + tire.getId() + "</td><td>" + tire.getName()+ "</td><td>" + tire.getPrice() + "</td></tr>";
        }
        retval += "</table></body></html>";
        
        return retval;
    }    
    
    /**
     * Finds Tire with specific id
     * @param id Id of Tire to be found
     * @return Tire found
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, headers = "Accept=text/html", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getTire(@PathVariable long id) {
        TireDTO tire = tireFacade.getTireById(id);
        String retval = "<html><body><h3>" + tire.toString() + "</h3></body></html>";
        return retval;      
    }
    
    /**
     * Creates new Tire with given parameters
     * curl -X POST -i -H "Content-Type: application/json" --data '{"id":1234,"name":"NameOfTire"}' http://localhost:8080/pa165/rest/tires/create
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void getTire(@RequestBody Tire tire) {
        Tires.add(tire);
    }    
}