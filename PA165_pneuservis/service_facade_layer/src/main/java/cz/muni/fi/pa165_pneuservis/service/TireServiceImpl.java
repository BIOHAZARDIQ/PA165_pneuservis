package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.PneuDAOException;
import cz.muni.fi.pa165_pneuservis.sort.TireSort;
import cz.muni.fi.pa165_pneuservis.dao.TireDao;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.sort.*;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer implementation for Tire
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Service
public class TireServiceImpl implements TireService {

    @Autowired
    private TireDao tireDao;

    /**
     * Creates Tire
     * @param tire
     * @throws PneuBusinessException
     */
    @Override
    public void createTire(Tire tire) throws PneuBusinessException {
        if(tire == null)
            throw new IllegalArgumentException("Tire can't be null.");
        if(tire.getName().isEmpty())
            throw new IllegalArgumentException("Tire name can't be empty.");        
        try {
            tireDao.create(tire);
        }
        catch(PneuDAOException e) {
            //TODO log
            throw new PneuBusinessException("Can't create Tire. Reason: " + 
                    e.getMessage(), e);
        }
    }

    /**
     * Retrieves all Tires in system sorted
     * @param sort
     * @return List of Tires
     * @throws PneuBusinessException
     */
    @Override
    public List<Tire> findAllTires(TireSort sort) throws PneuBusinessException{
        if(sort == null)
            throw new IllegalArgumentException("Sort can't be null.");
        List<Tire> tires;
        try {
            tires = tireDao.findAll();
        }
        catch(PneuDAOException e) {
            //TODO log
            throw new PneuBusinessException("Can't list Tires. Reason: " + 
                    e.getMessage(), e);
        }
        if (tires.isEmpty()) {
            throw new PneuBusinessException("There are no Tires in system.");
        }
        switch(sort)
        {
            //TODO acending/descending
            case BRAND:
                Collections.sort(tires, new TireSortByBrandComp());
                break;
            case PRICE:
                Collections.sort(tires, new TireSortByPriceComp());
                break;
            case RIM:
                Collections.sort(tires, new TireSortByRimComp());
                break;
            case WIDTH:
                Collections.sort(tires, new TireSortByWidthComp());
                break;
        }
        return tires;
    }

    @Override
    public Tire getTireById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long updateTire(Tire tire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deleteTire(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
