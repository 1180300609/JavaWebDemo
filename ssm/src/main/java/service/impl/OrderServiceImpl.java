package service.impl;

import dao.MenuDao;
import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Menu;
import po.Order;
import service.OrderService;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Order> getOrderList() {
        return orderDao.getOrderList();
    }

    @Override
    public List<Order> getOrderListByCustomer(String customer) {
        if (customer == null || customer.equals("")) {
            return null;
        } else {
            return orderDao.getOrderListByCustomer(customer);
        }
    }

    @Override
    public List<Order> getOrderListByCook(String cook) {
        if (cook == null || cook.equals("")) {
            return null;
        } else {
            return orderDao.getOrderListByCook(cook);
        }
    }

    @Override
    public int addOrder(Order order, List<Integer> items) {
        double total = 0;
        for (int i : items) {
            Menu menu = menuDao.selectMenuByID(i);
            total += menu.getPrice();
        }
        order.setTotal(total);
        int res = orderDao.addOrderStepOne(order);
        int orderId = orderDao.getLastID();
        for (int i : items) {
            int random = (int) (Math.random() * 100000);
            orderDao.addOrderStepTwo(orderId, i, random);
        }
        return res;
    }

    @Override
    public int deleteOrder(Integer orderId) {
        return orderDao.deleteOrder(orderId);
    }

    @Override
    public int setOrderCook(Order order) {
        return orderDao.setOrderCook(order);
    }

    @Override
    public int setOrderDone(Integer orderId) {
        Order order = orderDao.getOrderByID(orderId);
        if (order.getCook() == null || order.getCook().equals("")) {
            return 0;
        }
        return orderDao.setOrderDone(orderId);
    }

    @Override
    public Order getOrderByID(Integer orderID) {
        return orderDao.getOrderByID(orderID);
    }


}
