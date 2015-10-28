package cz.muni.fi.pa165_pneuservis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Jakub Holy
 */
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Item item;
    
    private Integer amount;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    @Override
    public int hashCode() {
        int result = 3;
        result = 41 * result + ((id == null)? 0 : id.hashCode());
        result = 41 * result + ((item == null)? 0 : item.hashCode());
        result = 41 * result + ((amount == null)? 0 : amount.hashCode());
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
        OrderItem otherObject = (OrderItem)o;
        
        if(!id.equals(otherObject.id)){
            return false;
        }
        if(!amount.equals(otherObject.amount)){
            return false;
        }
        if(!item.equals(otherObject.item)){
            return false;
        }
        return true;
    }
}
