package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import po.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList();

    List<Order> getOrderListByCustomer(String customer);

    int addOrder(Order order, List<Integer> items);

    int deleteOrder(Integer orderId);

    int setOrderCook(Order order);

    int setOrderDone(Integer orderId);
}
