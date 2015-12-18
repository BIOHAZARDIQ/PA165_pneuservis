/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dao.TireDao;
import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.TireService;
import cz.muni.fi.pa165_pneuservis.enums.TireSort;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Facade implementation for Tire
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Service
@Transactional
public class TireFacadeImpl implements TireFacade{

    @Autowired
    private TireService tireService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private TireDao tireDao;
    
    @Override
    public void createTire(TireDTO tire){
        try{
            tireService.createTire(beanMappingService.mapTo(tire, Tire.class));
        }catch(PneuBusinessException e)
        {
            //TODO log, throw facade layer exception
        }        
    }

    @Override
    public List<TireDTO> findAllTires() {
        List<TireDTO> tires = null;
        try{
            tires = beanMappingService.mapTo(tireService.findAllTires(TireSort.PRICE, true), 
                    TireDTO.class);
        }catch(PneuBusinessException e)
        {
            //TODO log, throw facade layer exception
        }
        return tires;
    }
    
    @Override
    public List<TireDTO> findAllTiresSorted(TireSort sort, boolean asc) {
        List<TireDTO> tires = null;
        try{
            tires = beanMappingService.mapTo(tireService.findAllTires(sort, asc), 
                    TireDTO.class);
        }catch(PneuBusinessException e)
        {
            //TODO log, throw facade layer exception
        }
        return tires;
    }

    @Override
    public TireDTO getTireById(Long id) {
        TireDTO tire = null;
        try{
            tire = beanMappingService.mapTo(tireService.getTireById(id), TireDTO.class);
        }catch(PneuBusinessException e)
        {
            //TODO log, throw facade layer exception
        }
        return tire;
    }

    @Override
    public void updateTire(TireDTO tire) {
        try{
            tireService.updateTire(beanMappingService.mapTo(tire, Tire.class));
        }catch(PneuBusinessException e)
        {
            //TODO log, throw facade layer exception
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTire(TireDTO tire) {
        try{
            //tireService.deleteTire(beanMappingService.mapTo(tire, Tire.class));
            //Workaround 
            tireService.deleteTire(tireDao.findById(tire.getId()));
        }catch(PneuBusinessException e)
        {
            //TODO log, throw facade layer exception
        }
    }
}
