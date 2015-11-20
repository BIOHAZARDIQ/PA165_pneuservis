package cz.muni.fi.pa165_pneuservis.dto;

/**
 *
 * @author Jakub Holy
 */

public class OrderItemDTO {

    private Long id;

    private ItemDTO item;
    
    private Integer amount;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
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
        int primeNumber = 41;
        result = primeNumber * result + ((id == null)? 0 : id.hashCode());
        result = primeNumber * result + ((item == null)? 0 : item.hashCode());
        result = primeNumber * result + ((amount == null)? 0 : amount.hashCode());
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
        OrderItemDTO otherObject = (OrderItemDTO)o;
        
        if(id == null){
            if(otherObject.id != null){
                return false;
            }
        }else if(!id.equals(otherObject.id)){
            return false;
        }
        if(amount == null){
            if(otherObject.amount != null){
                return false;
            }
        }else if(!amount.equals(otherObject.amount)){
            return false;
        }
        if(item == null){
            if(otherObject.item != null){
                return false;
            }
        }else if(!item.equals(otherObject.item)){
            return false;
        }
        return true;
    }
}
