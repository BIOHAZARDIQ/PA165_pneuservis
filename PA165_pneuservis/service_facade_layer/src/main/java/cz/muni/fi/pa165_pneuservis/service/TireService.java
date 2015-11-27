/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.sort.TireSort;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.List;

/**
 * Service layer interface for Tire
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public interface TireService {
    /**
     * Creates new Tire
     * @param tire Tire to be created
     * @throws PneuBusinessException
     */
    void createTire(Tire tire) throws PneuBusinessException;
    
    /**
     * Retrieves all Tires in system sorted
     * @param sort Type of sort to be applied on resulting collection
     * @return List of Tires
     * @throws PneuBusinessException
     */
    List<Tire> findAllTires(TireSort sort) throws PneuBusinessException;
    
    /**
     * Retrieves Tire by it's identifier
     * @param id Tire's ID
     * @return Tire Selected Tire
     * @throws PneuBusinessException
     */
    Tire getTireById(Long id) throws PneuBusinessException;
    
    /**
     * Updates Tire with modified attributes
     * @param tire Tire to be updated
     * @throws PneuBusinessException
     */
    void updateTire(Tire tire) throws PneuBusinessException;
    
    /**
     * Removes Tire from the system
     * @param tire Tire to be removed
     * @throws PneuBusinessException
     */
    void deleteTire(Tire tire) throws PneuBusinessException;
}
