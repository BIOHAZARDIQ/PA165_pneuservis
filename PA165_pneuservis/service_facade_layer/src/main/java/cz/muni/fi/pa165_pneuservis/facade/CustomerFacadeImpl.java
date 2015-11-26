/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.service.BeanMappingService;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Komoi
 */
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
    public CustomerDTO findCustomerByEmail(String email) {
        return beanMappingService.mapTo(customerService.findCustomerByEmail(email), CustomerDTO.class);
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
}