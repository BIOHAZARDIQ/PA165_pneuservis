
package cz.muni.fi.pa165_pneuservis;

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
    }
}
