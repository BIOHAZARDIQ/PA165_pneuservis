/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dto;
import javax.validation.constraints.*;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
public class TireDTO extends ItemDTO {

    @NotNull(message = "Can't be empty")
    @Size(min = 2, max = 32, message="Brand name must be between 2 and 32 characters")
    private String brand;
    
    @NotNull(message = "Can't be empty")
    @Max(value = 1000, message = "Maximum is 1250")
    @Min(value = 100, message = "Must at least 100")
    private Integer width;
    
    @NotNull(message = "Can't be empty")
    @Max(value = 80, message = "Maximum is 80")
    @Min(value = 30, message = "Must be at least 30")
    private Integer ratio;
    
    @NotNull(message = "Can't be empty")
    @Max(value = 30, message = "Maximum is 30")
    @Min(value = 10, message = "Must be at least 10")
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
        
        TireDTO otherObject = (TireDTO)o;
        
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
