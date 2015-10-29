/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.List;

/**
 *
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
public interface CustomerDao {
    public void createCustomer(Customer customer);
    public void modifyCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
    public Customer findCustomerById(Long id);
    public List<Customer> findAll();
}
