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
    public void create(Customer customer);
    public void modify(Customer customer);
    public void remove(Customer customer);
    public Customer findById(Long id);
    public List<Customer> findAll();
}
