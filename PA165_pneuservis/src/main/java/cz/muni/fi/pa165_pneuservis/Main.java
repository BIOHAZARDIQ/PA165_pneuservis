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

        
        task04();
	
        entityManagerFactory.close();
        
        
    }
    
    private static void task04() {
		// TODO under this line, persist two categories, one with name
		// Electronics and second with name Musical
		// You must first obtain the Entity manager 
		EntityManager em1 = entityManagerFactory.createEntityManager();
		em1.getTransaction().begin();
		Tire tire = new Tire();
                tire.setBrand(null);
                tire.setWidth(Integer.SIZE);
                tire.setRatio(Integer.SIZE);
                tire.setRim(Integer.SIZE);
                
                tire.setId(Long.valueOf(0));
                tire.setName("Electronics");
                tire.setDescription(null);
                tire.setBrand(null);
                
                Tire tire2 = new Tire();
                em1.persist(tire);
                
                tire2.setId(Long.valueOf(1));
                tire2.setName("Musical");
                em1.persist(tire2);

                
                

		em1.getTransaction().commit();
		em1.close();
		
		// The code below is just testing code do not modify it
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		List<Tire> categories = em.createQuery(
				"select c from Tire c order by c.name", Tire.class)
				.getResultList();

		assertEq(categories.get(0).getName(), "Electronics");
		assertEq(categories.get(1).getName(), "Musical");

		em.getTransaction().commit();
		em.close();

		System.out.println("Succesfully found Electronics and Musical!");
	}
    
    private static void assertEq(Object obj1, Object obj2) {
		if (!obj1.equals(obj2)) {
			throw new RuntimeException(
					"Expected these two objects to be identical: " + obj1
							+ ", " + obj2);
		} else {
			System.out.println("OK objects are identical");
		}
	}
}
