package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import java.util.List;

/**
 *
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public interface ServiceFacade {
    
    public ServiceDTO findServiceById(Long id);
    public List<ServiceDTO> findAllService();
    public void createService(ServiceDTO serviceDTO);
    public void updateService(Long id);
    public void deleteService(Long id);
    
}
