/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.service;

/**
 * Custom exception describing errors in Business layer
 * 
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class PneuBusinessException extends Exception {
    
    public PneuBusinessException(String msg) {
        super("Error occured in Business/Service layer: " + msg);
    }

    public PneuBusinessException(String msg, Throwable cause) {
        super("Error occured in Business/Service layer: " + msg, cause);
    }
}
