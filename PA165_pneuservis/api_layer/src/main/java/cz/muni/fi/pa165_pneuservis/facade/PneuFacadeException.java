/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.facade;

/**
 * Custom exception describing errors in Facade layer
 * 
 * @author Jozef.Sumaj
 */
public class PneuFacadeException extends Exception {
    
    public PneuFacadeException(String msg) {
        super("Error occured in Facade layer: " + msg);
    }

    public PneuFacadeException(String msg, Throwable cause) {
        super("Error occured in Facade layer: " + msg, cause);
    }
}
