/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import java.util.Collection;

/**
 *
 * @author Jakub Holy
 */
public interface CustomerFacade {
    
    public CustomerDTO findCustomerById(Long id);
    public CustomerDTO findCustomerByEmail(String email);
    public Collection<CustomerDTO> findAllCustomers();
    public void createCustomer(CustomerDTO customer);
    public void deleteCustomer(Long id);
}
