/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.data;

import cz.muni.fi.pa165_pneuservis.config.ServiceConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackageClasses={PrepareEnvironmentFacadeImpl.class})
public class DataConfig {
    
    @Autowired
    PrepareEnvironmentFacade environmentFacade;
    
    @PostConstruct
    public void dataLoading() {
        environmentFacade.PrepareEnvironment();
    }
}
