package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.dao.OrderItemDaoImpl;
import cz.muni.fi.pa165_pneuservis.dto.OrderDTO;
import cz.muni.fi.pa165_pneuservis.facade.OrderFacadeImpl;
import cz.muni.fi.pa165_pneuservis.model.Order;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
  * @author Jozef.Sumaj
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={OrderItemDaoImpl.class, OrderFacadeImpl.class})
public class ServiceConfiguration {
    
    @Bean
    public Mapper Dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();		
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }
	
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Order.class, OrderDTO.class);
        }
    }
}
