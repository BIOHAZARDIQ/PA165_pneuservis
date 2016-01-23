/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public CustomerDTO findCustomerById(Long id) {
        return beanMappingService.mapTo(customerService.findCustomerById(id), CustomerDTO.class);
    }
    
    @Override
    public CustomerDTO findCustomerByEmail(String email) throws PneuFacadeException {
        CustomerDTO customerDTO = null;
        try{
            customerDTO = beanMappingService.mapTo(customerService.findCustomerByEmail(email), CustomerDTO.class);
        }catch(PneuBusinessException e)
        {
            //TODO log
            throw new PneuFacadeException("Can't find Customer using specified email. Reason: " +
                    e.getMessage(), e);
        }
        return customerDTO;
    }
    
    @Override
    public Collection<CustomerDTO> findAllCustomers() {
        return beanMappingService.mapTo(customerService.findAllCustomers(), CustomerDTO.class);
        
    }
    
    @Override
    public void createCustomer(CustomerDTO customer) {
        customerService.createCustomer(beanMappingService.mapTo(customer, Customer.class));
    }
    
    @Override
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }

    @Override
    public CustomerDTO authenticate(String email, String password) throws PneuFacadeException {
        CustomerDTO customer = findCustomerByEmail(email);
        boolean valid = BCrypt.checkpw(password, customer.getPassword());
        
        if(valid)
            return customer;
        
        //Unauthorized request //TOTO log
        throw new PneuFacadeException("Customer email and password doesn't match.");
    }
}