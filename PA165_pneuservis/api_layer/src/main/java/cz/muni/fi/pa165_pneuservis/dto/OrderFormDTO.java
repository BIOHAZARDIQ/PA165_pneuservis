/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * DTO for order form
 * @author Jozef Sumaj
 */
public class OrderFormDTO {
    @NotNull(message = "Can't be empty")
    private List<Long> serviceIds;
    @NotNull(message = "Can't be empty")
    private List<Long> tireIds;
    
    public void setServiceIds(List<Long> serviceIds){
        this.serviceIds = serviceIds;
    }
    
    public List<Long> getServiceIds(){
        return serviceIds;
    }
    
    public void setTireIds(List<Long> tireIds){
        this.tireIds = tireIds;
    }
    
    public List<Long> getTireIds(){
        return tireIds;
    }
}
