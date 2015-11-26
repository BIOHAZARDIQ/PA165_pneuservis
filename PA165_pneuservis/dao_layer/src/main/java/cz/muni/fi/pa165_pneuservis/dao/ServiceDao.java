/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Service;
import java.util.List;

/**
 * Describes data access object layer for entity Service
 *
 * @author Jozef Sumaj
 */
public interface ServiceDao {
    
    /**
     * @param service
     */
    public void create(Service service);
    
    /**
     * @param service
     */
    public void update(Service service);
    
    /**
     * @param service
     */
    public void remove(Service service);
    
    /**
     * @param id
     * @return
     */
    public Service findById(Long id);
    
    /**
     * @return
     */
    public List<Service> findAll();
}
