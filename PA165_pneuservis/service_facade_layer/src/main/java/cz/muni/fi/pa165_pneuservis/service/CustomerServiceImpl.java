/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderItemDao;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Komoi
 */
public class CustomerServiceImpl implements CustomerService {
    
    
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer findCustomerById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Override
    public Collection<Customer> findAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public void createCustomer(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerDao.remove(customerDao.findById(id));
    }
    
}
