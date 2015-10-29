/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.model;

import javax.persistence.Entity;

/**
 *
 * @author Jozef.Sumaj
 */
@Entity
public class Tire extends Item {
    private Long id;
    private String brand;
    private Integer width;
    private Integer ratio;
    private Integer rim;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
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
        result = 41 * result + ((id == null)? 0 : id.hashCode());
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
        
        if(!id.equals(otherObject.id)){
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
