/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.List;

/**
 * Describes data access object layer for entity Tire
 *
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public interface TireDao {
    
    /**
     * @param tire
     */
    public void create(Tire tire);
    
    /**
     * @param tire
     */
    public void update(Tire tire);
    
    /**
     * @param tire
     */
    public void remove(Tire tire);
    
    /**
     * @param id
     * @return
     */
    public Tire findById(Long id);
    
    /**
     * @return
     */
    public List<Tire> findAll();
}
