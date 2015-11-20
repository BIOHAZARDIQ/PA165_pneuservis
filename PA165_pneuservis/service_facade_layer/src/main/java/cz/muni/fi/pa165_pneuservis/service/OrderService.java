package cz.muni.fi.pa165_pneuservis.service;

import cz.muni.fi.pa165_pneuservis.model.Order;
import java.util.List;


/**
 *
 * @author Komoi
 */
public interface OrderService {
        void createOrder(Order order);
        List<Order> findAllOrders();

        //and many more...
}
