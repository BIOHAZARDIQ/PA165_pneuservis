/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dao;

import org.springframework.dao.DataAccessException;

/**
 * Custom exception describing errors in DAO layer
 * 
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class PneuDAOException extends DataAccessException {
    
    public PneuDAOException(String msg) {
        super("Error occured in DAO layer: " + msg);
    }

    public PneuDAOException(String msg, Throwable cause) {
        super("Error occured in DAO layer: " + msg, cause);
    }
}
