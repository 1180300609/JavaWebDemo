package dao;

import po.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrderList();

    int addOrderStepOne(Order order);

    int addOrderStepTwo(Integer orderId,Integer itemId,Integer random);

    int deleteOrder(Integer orderId);

    int setOrderCook(Order order);

    int setOrderDone(Integer orderId);

    List<Order> getOrderListByCustomer(String customer);

    List<Order> getOrderListByCook(String cook);

    int getLastID();

    Order getOrderByID(Integer orderID);
}
