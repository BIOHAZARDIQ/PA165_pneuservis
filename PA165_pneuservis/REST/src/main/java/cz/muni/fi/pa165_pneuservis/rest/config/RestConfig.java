/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.rest.config;

import cz.muni.fi.pa165_pneuservis.rest.controllers.TireController;
import cz.muni.fi.pa165_pneuservis.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@EnableWebMvc
@Configuration
@Import({ServiceConfig.class})
@ComponentScan(basePackageClasses = {TireController.class})
public class RestConfig extends WebMvcConfigurerAdapter {
}
