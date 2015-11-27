package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.model.Service;
import java.util.List;

/**
 *
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public interface ServiceService {
    
    public Service findServiceById(Long id);
    public List<Service> findAllService();
    public void createService(Service service);
    public void updateService(Long id);
    public void deleteService(Long id);
}
