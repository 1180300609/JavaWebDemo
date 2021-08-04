package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.transaction.annotation.Transactional;
import po.Order;

import java.util.List;

@Transactional
public interface OrderService {
    List<Order> getOrderList();

    List<Order> getOrderListByCustomer(String customer);

    List<Order> getOrderListByCook(String cook);

    int addOrder(Order order, List<Integer> items);

    int deleteOrder(Integer orderId);

    int setOrderCook(Order order);

    int setOrderDone(Integer orderId);

    Order getOrderByID(Integer orderID);
}
