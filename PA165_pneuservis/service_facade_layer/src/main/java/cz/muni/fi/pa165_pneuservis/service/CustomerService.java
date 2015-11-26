/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.Collection;

/**
 *
 * @author Komoi
 */
public interface CustomerService  {
    
    public Customer findCustomerById(Long id);
    public Customer findCustomerByEmail(String email);
    public Collection<Customer> findAllCustomers();
    public void createCustomer(Customer customer);
    public void deleteCustomer(Long id);
    
}
