/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.config;

import cz.muni.fi.pa165_pneuservis.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165_pneuservis.facade.TireFacadeImpl;
import cz.muni.fi.pa165_pneuservis.service.OrderServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Jozef.Sumaj
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan
public class ServiceConfig {
    	
        @Bean
	public Mapper dozer(){
            DozerBeanMapper dozer = new DozerBeanMapper();		
            return dozer;
	}
}
