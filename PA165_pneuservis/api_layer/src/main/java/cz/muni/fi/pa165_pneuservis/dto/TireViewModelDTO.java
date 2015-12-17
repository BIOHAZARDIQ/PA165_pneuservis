/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jozef.Sumaj
 */
public class TireViewModelDTO extends ItemDTO {
    
    @NotNull
    @Size(min = 3, max = 50)
    private String brand;
    private Integer width;
    private Integer ratio;
    private Integer rim;
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public Integer getWidth() {
        return width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public Integer getRatio() {
        return ratio;
    }
    
    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }
    
    public Integer getRim() {
        return rim;
    }
    
    public void setRim(Integer rim) {
        this.rim = rim;
    }
    
    @Override
    public int hashCode() {
        int result = 3;
        result = 41 * result + ((this.getId() == null)? 0 : this.getId().hashCode());
        result = 41 * result + ((brand == null)? 0 : brand.hashCode());
        result = 41 * result + ((width == null)? 0 : width.hashCode());
        result = 41 * result + ((ratio == null)? 0 : ratio.hashCode());
        result = 41 * result + ((rim == null)? 0 : rim.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) {
            return true;
        }
        
        if((o == null) || (getClass() != o.getClass())){
            return false;
        }
        
        TireViewModelDTO otherObject = (TireViewModelDTO)o;
        
        if(!this.getId().equals(otherObject.getId())){
            return false;
        }
        
        if(!brand.equals(otherObject.brand)){
            return false;
        }
        
        if(!width.equals(otherObject.width)){
            return false;
        }
        
        if(!ratio.equals(otherObject.ratio)){
            return false;
        }
        
        if(!rim.equals(otherObject.rim)){
            return false;
        }
        
        return true;
    }
    
}
