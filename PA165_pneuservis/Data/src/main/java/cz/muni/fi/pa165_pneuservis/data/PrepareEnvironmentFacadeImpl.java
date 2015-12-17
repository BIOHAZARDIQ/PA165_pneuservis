/*
* Team project for course PA165 - Enterprise Applications in Java
* For more informations see file README.md
*/
package cz.muni.fi.pa165_pneuservis.data;

import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.TireService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Component
@Transactional
public class PrepareEnvironmentFacadeImpl implements PrepareEnvironmentFacade {
    
    @Autowired
    private TireService tireService;
    
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
    
    /**
     * Fills system with test data
     */
    @Override
    public void PrepareEnvironment() {
        
        //List of names that will be randomly selected when creating Tires
        ArrayList<String> tireBrand = new ArrayList<>(Arrays.asList("Michelin","Yokohama","Pirelli","Goodyear","Barum"));
        ArrayList<Integer> tireWidth = new ArrayList<>(Arrays.asList(155,165,175,185,195));
        ArrayList<Integer> tireRatio = new ArrayList<>(Arrays.asList(55,60,65,70,75));
        ArrayList<Integer> tireRim = new ArrayList<>(Arrays.asList(14,15,16,17,18,19));
        
        Tire tire = null;
        randomGenerator = new Random();
        
        //create 30 Tires with random parameters
        for (long i = 1; i <= 30; i++) {
            tire = new Tire();
            
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
            tire.setName(name);
            tire.setBrand(brand);
            tire.setDescription(description);
            tire.setWidth(width);
            tire.setRatio(ratio);
            tire.setRim(rim);
            tire.setPrice(priceBigDecimal);
            
            try {
                tireService.createTire(tire);
            } catch (PneuBusinessException ex) {
                //TODO log
            }
        }
    }
}
