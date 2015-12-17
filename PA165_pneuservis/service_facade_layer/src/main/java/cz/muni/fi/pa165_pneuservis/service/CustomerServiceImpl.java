/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.dao.CustomerDao;
import cz.muni.fi.pa165_pneuservis.dao.OrderDao;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
@Service
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