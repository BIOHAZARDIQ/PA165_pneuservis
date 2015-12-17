/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.data;

import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.TireService;
import java.math.BigDecimal;
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
    
    /**
     * Fills system with test data
     */
    @Override
    public void PrepareEnvironment() {
        Tire t1;
        t1 = new Tire();
        t1.setBrand("Barum");
        t1.setPrice(BigDecimal.valueOf(799.00));
        t1.setWidth(225);
        t1.setRatio(50);
        t1.setRim(17);
        try {
            tireService.createTire(t1);
        } catch (PneuBusinessException ex) {
            //TODO log
        }
    }    
}
