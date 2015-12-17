/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.data;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Configuration
@ComponentScan
public class DataConfig {
    
    @Autowired
    PrepareEnvironmentFacade environmentFacade;
    
    @PostConstruct
    public void Load() {
        environmentFacade.PrepareEnvironment();
    }
}