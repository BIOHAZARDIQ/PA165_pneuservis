/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.TireService;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import static cz.muni.fi.pa165_pneuservis.service.helper.ServiceTestHelper.toList;
import cz.muni.fi.pa165_pneuservis.sort.TireSort;
import java.util.List;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Tire facade layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class TireFacadeImplTest {
    
    @Mock
    private TireService tireServiceMock;
    
    @Mock
    private BeanMappingService beanMappingServiceMock;
    
    @InjectMocks
    private final TireFacade facade = new TireFacadeImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
          
    @Test
    public void testFindAllTiresFacade() throws PneuBusinessException {
        Tire t1 = new Tire();
        Tire t2 = new Tire();
        TireDTO dto1 = new TireDTO();
        TireDTO dto2 = new TireDTO();
        
        doReturn(toList(new Tire[]{t1,t2})).when(tireServiceMock).findAllTires(TireSort.PRICE);
        doReturn(toList(new TireDTO[]{dto1,dto2})).when(beanMappingServiceMock)
                .mapTo(Matchers.anyListOf(Tire.class),(Class<?>)Matchers.any(Class.class));
        
        List<TireDTO> tiresDTO = facade.findAllTires();
        Assert.assertEquals(tiresDTO.size(), 2);
        
        verify(tireServiceMock).findAllTires(TireSort.PRICE);
        verifyNoMoreInteractions(tireServiceMock);
    }
}
