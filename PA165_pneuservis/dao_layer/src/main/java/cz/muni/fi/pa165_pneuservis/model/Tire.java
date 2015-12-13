/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
//1import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Entity
//@JsonAutoDetect
public class Tire extends Item {
    
    private String brand;
    private Integer width;
    private Integer ratio;
    private Integer rim;
    
    public Tire(Long id, String name, String description, String brand, Integer width, Integer ratio, Integer rim, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.width = width;
        this.ratio = ratio;
        this.rim   = rim;
        this.price = price;
    }
    
    public Tire() {
        this.id = (long) 123;
        this.name = "Default name for Tire from constructor";
    }
    
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
        
        Tire otherObject = (Tire)o;
        
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
