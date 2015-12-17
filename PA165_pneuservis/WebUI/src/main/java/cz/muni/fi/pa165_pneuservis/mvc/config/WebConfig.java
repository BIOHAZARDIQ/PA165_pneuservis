/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.config;

import cz.muni.fi.pa165_pneuservis.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@EnableWebMvc
@Configuration
@Import({ServiceConfig.class})
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter{
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("tire/list");
    }    
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {    
        configurer.enable();
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}