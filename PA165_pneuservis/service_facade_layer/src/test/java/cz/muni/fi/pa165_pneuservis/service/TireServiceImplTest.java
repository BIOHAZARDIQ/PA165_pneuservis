/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.TireDao;
import cz.muni.fi.pa165_pneuservis.dao.PneuDAOException;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.enums.TireSort;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import org.testng.Assert;
import java.util.List;

import static cz.muni.fi.pa165_pneuservis.service.helper.ServiceTestHelper.toList;
import org.mockito.Matchers;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Tire service layer tests
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class TireServiceImplTest {
 
    @Mock
    private TireDao tireDaoMock;
    
    @InjectMocks
    private final TireService service = new TireServiceImpl();
    
    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        t1 = new Tire();
        t1.setId(1L);
        t1.setBrand("Barum");
        t1.setPrice(BigDecimal.valueOf(799.00));
        t1.setWidth(225);
        t1.setRatio(50);
        t1.setRim(17);
        
        t2 = new Tire();
        t2.setId(2L);
        t2.setBrand("Nokian");
        t2.setPrice(BigDecimal.valueOf(899.00));
        t2.setWidth(195);
        t2.setRatio(55);
        t2.setRim(16);
        
        t3 = new Tire();
        t3.setId(3L);
        t3.setBrand("Continental");
        t3.setPrice(BigDecimal.valueOf(749.00));
        t3.setWidth(185);
        t3.setRatio(65);
        t3.setRim(15);
        
        doReturn(toList(new Tire[]{t1,t2,t3})).when(tireDaoMock).findAll();
    }
    
    private Tire t1,t2,t3;
    
    @Test
    public void testFindAllTiresSortByPrice() throws PneuBusinessException{
        List<Tire> tires = service.findAllTires(TireSort.PRICE, true);
        verify(tireDaoMock).findAll();
        verifyNoMoreInteractions(tireDaoMock);
        Assert.assertEquals(tires.get(0).getId(), t3.getId()); //contin first
        Assert.assertEquals(tires.get(2).getId(), t2.getId()); //nokian last
    }
    
    @Test
    public void testFindAllTiresSortByBrand() throws PneuBusinessException{
        List<Tire> tires = service.findAllTires(TireSort.BRAND, true);
        verify(tireDaoMock).findAll();
        verifyNoMoreInteractions(tireDaoMock);
        Assert.assertEquals(tires.get(0).getId(), t1.getId()); //barum first
        Assert.assertEquals(tires.get(2).getId(), t2.getId()); //nokian last
    }
        
    @Test
    public void testFindAllTiresSortByRim() throws PneuBusinessException{
        List<Tire> tires = service.findAllTires(TireSort.RIM, true);
        verify(tireDaoMock).findAll();
        verifyNoMoreInteractions(tireDaoMock);
        Assert.assertEquals(tires.get(0).getId(), t3.getId()); //contin first
        Assert.assertEquals(tires.get(2).getId(), t1.getId()); //barum last
    }
    
    @Test
    public void testFindAllTiresSortByWidth() throws PneuBusinessException{
        List<Tire> tires = service.findAllTires(TireSort.WIDTH, true);
        verify(tireDaoMock).findAll();
        verifyNoMoreInteractions(tireDaoMock);
        Assert.assertEquals(tires.get(0).getId(), t3.getId()); //contin first
        Assert.assertEquals(tires.get(2).getId(), t1.getId()); //barum last
    }
    
    @Test
    public void testCreateTire() throws PneuBusinessException{
        Tire tire = new Tire();
        tire.setName("CONTINENTAL PremiumContact5");
        service.createTire(tire);
        verify(tireDaoMock).create(tire);
        verifyNoMoreInteractions(tireDaoMock);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateTireInvalidParamNull() throws PneuBusinessException{
        service.createTire(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateTireInvalidParamNameNull() throws PneuBusinessException{
        Tire tire = new Tire();
        service.createTire(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateTireInvalidParamNameEmpty() throws PneuBusinessException{
        Tire tire = new Tire();
        tire.setName("");
        service.createTire(tire);
    }
    
    @Test(expectedExceptions = PneuBusinessException.class)
    public void testCreateTireDAOError() throws PneuBusinessException{
        doThrow(PneuDAOException.class).when(tireDaoMock).create(Matchers.any(Tire.class));
        Tire tire = new Tire();
        tire.setName("CONTINENTAL PremiumContact5");
        service.createTire(tire);
    }
    
    @Test
    public void testDeleteTire() throws PneuBusinessException{
        Tire tire = new Tire();       
        service.deleteTire(tire);
        verify(tireDaoMock).remove(tire);
        verifyNoMoreInteractions(tireDaoMock);
    }
    
    @Test
    public void testUpdateTire() throws PneuBusinessException{
        Tire tire = new Tire();       
        service.updateTire(tire);
        verify(tireDaoMock).update(tire);
        verifyNoMoreInteractions(tireDaoMock);
    }
    
    public void getTireById() throws PneuBusinessException{
        Tire t1 = new Tire();
        t1.setId(1L);
        Tire tire = service.getTireById(1L);
        verify(tireDaoMock).findById(1L);
        verifyNoMoreInteractions(tireDaoMock);
    }
}
