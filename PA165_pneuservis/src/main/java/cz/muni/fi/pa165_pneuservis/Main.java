/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis;

import cz.muni.fi.pa165_pneuservis.model.SpringDatabase;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
public class Main {
    
    private static EntityManagerFactory entityManagerFactory;
    
    public static void main(String[] args) {
        System.out.println("Hello PA165 project!");
        
            new AnnotationConfigApplicationContext(SpringDatabase.class);


        entityManagerFactory = Persistence.createEntityManagerFactory("default");

       	
        entityManagerFactory.close();
        
        
    }
 
}
