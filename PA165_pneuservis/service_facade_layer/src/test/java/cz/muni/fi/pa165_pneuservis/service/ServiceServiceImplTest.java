package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.ServiceDao;
import cz.muni.fi.pa165_pneuservis.model.Service;
import static cz.muni.fi.pa165_pneuservis.service.helper.ServiceTestHelper.toList;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public class ServiceServiceImplTest {
    
    @Mock
    private ServiceDao serviceDao;
    
    @InjectMocks
    private ServiceService serviceService = new ServiceServiceImpl();
    
    Service service1,service2;
    
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        service1 = new Service();
        service1.setId(1L);
        service2 = new Service();
        service2.setId(2L);
    }
    
    @Test
    public void testInjectedServiceDao(){
        assertNotNull(serviceDao);
    }
    
    @Test
    public void testCreateService(){
        serviceService.createService(service1);
        verify(serviceDao).create(service1);
        verifyNoMoreInteractions(serviceDao);
    }

    @Test
    public void testFindServiceById() {       
        when(serviceDao.findById(1L)).thenReturn(service1);
        Service s = serviceService.findServiceById(1L);
        verify(serviceDao).findById(1L);
        Assert.assertEquals(new Long(1L), s.getId());
    }
    
    @Test
    public void testRemoveService(){
        when(serviceDao.findById(1L)).thenReturn(service1);
        serviceService.deleteService(1L);
        verify(serviceDao).findById(1L);
        verify(serviceDao).remove(service1);
        verifyNoMoreInteractions(serviceDao);
    }
    
    @Test
    public void testUpdateService(){
        when(serviceDao.findById(1L)).thenReturn(service1);
        serviceService.updateService(1L);
        verify(serviceDao).findById(1L);
        verify(serviceDao).update(service1);
        verifyNoMoreInteractions(serviceDao);
    }
    
    
    @Test
    public void testFindAllService(){
        when(serviceDao.findAll()).thenReturn(toList(new Service[]{service1,service2}));
        List<Service> listServices = serviceService.findAllService();
        
        verify(serviceDao).findAll();
        verifyNoMoreInteractions(serviceDao);
        Assert.assertEquals(2, listServices.size());
    }
}
