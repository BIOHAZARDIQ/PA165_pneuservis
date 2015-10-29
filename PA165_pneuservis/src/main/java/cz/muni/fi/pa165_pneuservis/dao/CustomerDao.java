/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
