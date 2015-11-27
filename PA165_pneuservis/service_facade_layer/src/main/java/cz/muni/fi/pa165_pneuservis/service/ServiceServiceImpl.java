package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.PneuDAOException;
import cz.muni.fi.pa165_pneuservis.dao.ServiceDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
@Service
public class ServiceServiceImpl implements ServiceService{
    
    @Autowired
    private ServiceDao serviceDao;
    
    @Override
    public cz.muni.fi.pa165_pneuservis.model.Service findServiceById(Long id) {
        try{
            return serviceDao.findById(id);
        }catch(DataAccessException e){
            throw new PneuDAOException("cannot find service"+e.getMessage(),e);
        }
    }

    @Override
    public List<cz.muni.fi.pa165_pneuservis.model.Service> findAllService(){
        try{
            return serviceDao.findAll();
        }catch(DataAccessException e){
            throw new PneuDAOException("cannot find all services"+e.getMessage(),e);
        }
    }

    @Override
    public void createService(cz.muni.fi.pa165_pneuservis.model.Service service) {
        try{
            serviceDao.create(service);
        }catch(DataAccessException e){
            throw new PneuDAOException("cannot create service "+e.getMessage(),e);
        }
    }

    @Override
    public void updateService(Long id) {
        try{
        serviceDao.update(serviceDao.findById(id));
        }catch(DataAccessException e){
            throw new PneuDAOException("cannot update service "+e.getMessage(),e);
        }
    }

    @Override
    public void deleteService(Long id) {
        try{
            serviceDao.remove(serviceDao.findById(id));
        }catch(DataAccessException e){
            throw new PneuDAOException("cannot remove service "+e.getMessage(),e);
        }
    }
    
}
