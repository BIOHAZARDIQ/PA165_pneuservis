package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.ServiceDTO;
import cz.muni.fi.pa165_pneuservis.model.Service;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.ServiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.MockitoAnnotations;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.when;
import org.testng.Assert;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public class ServiceFacadeImplTest {

    @Mock
    private BeanMappingService beanMappingServiceMock;
    
    @Mock
    private ServiceService serviceService;
    
    @InjectMocks
    private ServiceFacade facade = new ServiceFacadeImpl();
    
    private ServiceDTO serviceDTO1,serviceDTO2;
    
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        serviceDTO1 = new ServiceDTO();
        serviceDTO1.setId(1L);
        
        serviceDTO2 = new ServiceDTO();
        serviceDTO2.setId(2L);
    }
    
    @Test
    public void testInjectedBeanService(){
        assertNotNull(beanMappingServiceMock);
        assertNotNull(serviceService);
    }

    @Test
    public void testCreateServiceFacade() {
        Service service = beanMappingServiceMock.mapTo(serviceDTO1, Service.class);
        facade.createService(serviceDTO1);
        verify(serviceService).createService(service);
        verifyNoMoreInteractions(serviceService);        
    }
    
    @Test
    public void testFindServiceByIdFacade(){
        Service service = beanMappingServiceMock.mapTo(serviceDTO1, Service.class);
        
        when(serviceService.findServiceById(1L)).thenReturn(service);
        when(beanMappingServiceMock.mapTo(service, ServiceDTO.class)).thenReturn(serviceDTO1);
        
        ServiceDTO servDTO = facade.findServiceById(1L);
        
        verify(serviceService).findServiceById(1L);
        verify(beanMappingServiceMock).mapTo(service, ServiceDTO.class);
        Assert.assertEquals(new Long(1L), servDTO.getId());
    }
    
    @Test
    public void testUpdateServiceFacade(){
        Service service = beanMappingServiceMock.mapTo(serviceDTO1, Service.class);
        facade.updateService(serviceDTO1);
        verify(serviceService).updateService(service);
        verifyNoMoreInteractions(serviceService); 
    }
    
    @Test
    public void testFindAllServicesFacade(){
        Service service1 = beanMappingServiceMock.mapTo(serviceDTO1, Service.class);
        Service service2 = beanMappingServiceMock.mapTo(serviceDTO2, Service.class);
        List<Service> services = new ArrayList<>();
        services.add(service1);
        services.add(service2);
        List<ServiceDTO> servicesDTO = new ArrayList<>();
        servicesDTO.add(serviceDTO1);
        servicesDTO.add(serviceDTO2);
        
        when(serviceService.findAllService()).thenReturn(services);
        doReturn(servicesDTO).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Service.class),(Class<?>)Matchers.any(Class.class));
        
        List<ServiceDTO> servicesDTO2 = facade.findAllService();
        
        verify(serviceService).findAllService();
        verifyNoMoreInteractions(serviceService);
        Assert.assertEquals(2,servicesDTO2.size());
    }
    
    @Test
    public void testRemoveServiceFacade(){
        facade.deleteService(1L);
        verify(serviceService).deleteService(1L);
        verifyNoMoreInteractions(serviceService); 
    }
}
