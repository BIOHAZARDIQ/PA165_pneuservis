/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

import cz.muni.fi.pa165_pneuservis.dto.TireDTO;
import java.util.List;

/**
 * Facade layer interface for Tire
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public interface TireFacade {
    /**
     * Creates new Tire
     * @param tire Tire to be created
     */
    void createTire(TireDTO tire);
    
    /**
     * Retrieves all Tires in system sorted
     * @return List of Tires
     */
    List<TireDTO> findAllTires();
    
    /**
     * Retrieves Tire by it's identifier
     * @param id Tire's ID
     * @return Tire Selected Tire
     */
    TireDTO getTireById(Long id);
    
    /**
     * Updates Tire with modified attributes
     * @param tire Tire to be updated
     */
    void updateTire(TireDTO tire);
    
    /**
     * Removes Tire from the system
     * @param tire Tire to be removed
     */
    void deleteTire(TireDTO tire);
}
