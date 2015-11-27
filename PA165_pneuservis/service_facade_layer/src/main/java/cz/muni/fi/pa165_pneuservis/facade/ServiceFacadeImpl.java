package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import cz.muni.fi.pa165_pneuservis.service.ServiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
@Service
@Transactional
public class ServiceFacadeImpl implements ServiceFacade{

    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public ServiceDTO findServiceById(Long id) {
        return beanMappingService.mapTo(serviceService.findServiceById(id), ServiceDTO.class);
    }

    @Override
    public List<ServiceDTO> findAllService() {
        return beanMappingService.mapTo(serviceService.findAllService(), ServiceDTO.class);
    }

    @Override
    public void createService(ServiceDTO serviceDTO) {
        serviceService.createService((cz.muni.fi.pa165_pneuservis.model.Service) beanMappingService.mapTo(serviceDTO, Service.class));
    }

    @Override
    public void updateService(Long id) {
        serviceService.updateService(id);
    }

    @Override
    public void deleteService(Long id) {
        serviceService.deleteService(id);
    }
    
}
