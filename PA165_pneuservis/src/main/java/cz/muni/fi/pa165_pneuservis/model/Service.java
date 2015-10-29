/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.model;

/**
 *
 * @author Jozef.Sumaj
 */
public class Service extends Item {
    private ServiceType serviceType;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
    
      @Override
    public int hashCode() {
        int result = 3;
        result = 41 * result + ((this.getId() == null)? 0 : this.getId().hashCode());
        result = 41 * result + ((serviceType == null)? 0 : serviceType.hashCode());
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
        Service otherObject = (Service)o;
        
        if(!this.getId().equals(otherObject.getId())){
            return false;
        }
        if(!serviceType.equals(otherObject.serviceType)){
            return false;
        }
        return true;
    }
}
