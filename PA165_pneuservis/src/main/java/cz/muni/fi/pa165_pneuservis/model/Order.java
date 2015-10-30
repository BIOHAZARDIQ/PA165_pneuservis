/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Jakub Holy
 */
@Entity
@Table(name="ITEM_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal totalPrice;    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date completeDate;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Customer customer;
    
    //@Enumerated
    //private List<VehicleType> vehicles = new ArrayList<VehicleType>();
    
    @OneToMany(cascade=CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
//    public List<VehicleType> getVehicles(){
//        return Collections.unmodifiableList(vehicles);
//    }
    
//    public void addVehicle(VehicleType vehicleType){
//        vehicles.add(vehicleType);
//    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
    
    @Override
    public int hashCode(){
        int result=3;
        int primeNumber=47;
        result = primeNumber * result + ((id == null) ? 0 : id.hashCode());
        result = primeNumber * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = primeNumber * result + ((customer == null) ? 0 : customer.hashCode());
        //result = primeNumber * result + ((vehicles == null) ? 0 : vehicles.hashCode());
        result = primeNumber * result + ((orderItems == null) ? 0 : orderItems.hashCode());
        result = primeNumber * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
        
        Order otherObject = (Order)o;
        
        if(id == null){
            if(otherObject.id != null){
                return false;
            }
        }else if(!id.equals(otherObject.id)){
            return false;
        }
        if(createDate == null){
            if(otherObject.createDate != null){
                return false;
            }
        }else if(!createDate.equals(otherObject.createDate)){
            return false;
        }
        if(customer == null){
            if(otherObject.customer != null){
                return false;
            }
        }else if(!customer.equals(otherObject.customer)){
            return false;
        }
        return true;
    }
}
