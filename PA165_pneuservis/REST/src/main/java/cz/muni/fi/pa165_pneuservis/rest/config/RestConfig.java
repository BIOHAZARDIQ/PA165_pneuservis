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
 * @author Jozef.Sumaj
 */
@EnableWebMvc
@Configuration
@Import({ServiceConfig.class})
@ComponentScan(basePackageClasses = {TireController.class})
public class RestConfig extends WebMvcConfigurerAdapter {
}
