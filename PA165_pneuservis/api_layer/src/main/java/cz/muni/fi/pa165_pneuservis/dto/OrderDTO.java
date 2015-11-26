/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Jakub Holy <436353@mail.muni.cz>
 */
public class OrderDTO {
    
    private Long id;
    private BigDecimal totalPrice;
    private Date createDate;
    private Date completeDate;
    private CustomerDTO customer;
    
    //@Enumerated
    //private List<VehicleType> vehicles = new ArrayList<VehicleType>(); 
    private List<OrderItemDTO> orderItems = new ArrayList<OrderItemDTO>();
    
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
    
    public CustomerDTO getCustomer(){
        return customer;
    }
    
    public void setCustomer(CustomerDTO customer){
        this.customer = customer;
    }
    
    //TODO enable this vehicle logic
//    public List<VehicleType> getVehicles(){
//        return Collections.unmodifiableList(vehicles);
//    }
    
//    public void addVehicle(VehicleType vehicleType){
//        vehicles.add(vehicleType);
//    }
    
    public List<OrderItemDTO> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
    
    public void addOrderItem(OrderItemDTO orderItem) {
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
        
        OrderDTO otherObject = (OrderDTO)o;
        
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
