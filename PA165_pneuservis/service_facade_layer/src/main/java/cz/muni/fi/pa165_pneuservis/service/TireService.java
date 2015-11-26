/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.sort.TireSort;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import java.util.Collection;

/**
 * Service layer interface for Tire
 * @author Jozef.Sumaj
 */
public interface TireService {
    void createTire(Tire tire);
    Collection<Tire> findAllTires(TireSort sort);
    public Tire getTireById(Long id);
    public Long updateTire(Tire tire);
    public void deleteTire(Long id);
}
