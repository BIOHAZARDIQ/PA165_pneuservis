/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.Collection;

/**
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
public interface CustomerService  {
    
    public Customer findCustomerById(Long id);

    /**
     * Retrieves Customer by it's email
     * @param email
     * @return Customer Selected customer
     * @throws PneuBusinessException
     */
    public Customer findCustomerByEmail(String email) throws PneuBusinessException;
    public Collection<Customer> findAllCustomers();
    public void createCustomer(Customer customer);
    public void deleteCustomer(Long id);
}
