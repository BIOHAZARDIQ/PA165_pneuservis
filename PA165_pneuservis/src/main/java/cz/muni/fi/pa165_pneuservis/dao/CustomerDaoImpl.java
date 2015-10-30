/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }
        em.persist(customer);
    }

    @Override
    public void modifyCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }

        if (em.find(Customer.class, customer.getId()) == null) {
            throw new IllegalArgumentException("customer is not in database");
        }
        em.merge(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }

        Customer customerToDelete = em.find(Customer.class, customer.getId());
        if (customerToDelete == null) {
            throw new IllegalArgumentException("customer is not in database");
        }

        em.remove(customerToDelete);
    }

    @Override
    public Customer findCustomerById(Long id) {
         if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        Customer foundCustomer = em.find(Customer.class, id);
        if (foundCustomer == null) {
            throw new IllegalArgumentException("customer is not in database");
        }
        return foundCustomer;
    }

    @Override
    public List<Customer> findAll() {
        Query query = em.createQuery("SELECT * FROM Customer C");
        List<Customer> allCustomers = query.getResultList();
        return allCustomers;    
    }   
}