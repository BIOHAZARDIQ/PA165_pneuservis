package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.sort.TireSort;
import cz.muni.fi.pa165_pneuservis.dao.TireDao;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.sort.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer implementation for Tire
 * @author Jozef.Sumaj
 */
@Service
public class TireServiceImpl implements TireService {

    @Autowired
    private TireDao tireDao;

    @Override
    public void createTire(Tire tire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Tire> findAllTires(TireSort sort) {
        List<Tire> tires = tireDao.findAll();
        switch(sort)
        {
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
    public void deleteTire(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long updateTire(Tire tire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
