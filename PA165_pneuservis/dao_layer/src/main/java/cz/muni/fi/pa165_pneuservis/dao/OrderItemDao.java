package cz.muni.fi.pa165_pneuservis.dao;

import cz.muni.fi.pa165_pneuservis.model.OrderItem;
import java.util.List;

/**
 *
 * @author Jakub Holy
 */
public interface OrderItemDao {
    
    public void create(OrderItem orderItem);
    public void update(OrderItem orderItem);
    public void remove(OrderItem orderItem);
    public OrderItem findById(Long id);
    public List<OrderItem> findAll();
}
