/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Filip Meszaros <436321@mail.muni.cz>
 */
@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String streetName;
    private Integer streetNumber;
    private String city;
    private String state;
    private String postalNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isAdmin;
    
    @OneToMany
    private List<Order> orders = new ArrayList<Order>();
    
    //Getters & Setters of class Customer
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getStreetName() {
        return streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public Integer getStreetNumber() {
        return streetNumber;
    }
    
    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPostalNumber() {
        return postalNumber;
    }
    
    public void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
        result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((postalNumber == null) ? 0 : postalNumber.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (! (obj instanceof Customer))
            return false;
        
        Customer other = (Customer) obj;
        
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        
        if (firstName == null) {
            if (other.getFirstName()!= null)
                return false;
        } else if (!firstName.equals(other.getFirstName()))
            return false;
        
        if (lastName == null) {
            if (other.getLastName()!= null)
                return false;
        } else if (!lastName.equals(other.getLastName()))
            return false;
        
        if (streetName == null) {
            if (other.getStreetName()!= null)
                return false;
        } else if (!streetName.equals(other.getStreetName()))
            return false;
        
        if (streetNumber == null) {
            if (other.getStreetNumber()!= null)
                return false;
        } else if (!streetNumber.equals(other.getStreetNumber()))
            return false;
        
        if (city == null) {
            if (other.getCity()!= null)
                return false;
        } else if (!city.equals(other.getCity()))
            return false;
        
        if (state == null) {
            if (other.getState()!= null)
                return false;
        } else if (!state.equals(other.getState()))
            return false;
        
        if (postalNumber == null) {
            if (other.getPostalNumber()!= null)
                return false;
        } else if (!postalNumber.equals(other.getPostalNumber()))
            return false;
        
        if (phoneNumber == null) {
            if (other.getPhoneNumber()!= null)
                return false;
        } else if (!phoneNumber.equals(other.getPhoneNumber()))
            return false;
        
        if (email == null) {
            if (other.getEmail()!= null)
                return false;
        } else if (!email.equals(other.getEmail()))
            return false;
        
        if (password == null) {
            if (other.getPassword()!= null)
                return false;
        } else if (!password.equals(other.getPassword()))
            return false;
        
        return true;
    }
}
