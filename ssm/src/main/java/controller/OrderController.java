package controller;

import dao.OrderDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import po.Menu;
import po.Order;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    @PostMapping("/customerOrderList")
    public List<Order> getCustomerOrderList(@RequestParam("customer") String username) {
        return orderService.getOrderListByCustomer(username);
    }

    @PostMapping("/cookOrderList")
    public List<Order> getCookOrderList(@RequestParam("cook") String username) {
        return orderService.getOrderListByCook(username);
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam("customer") String customer, @RequestParam("menuList") ArrayList<Integer> menuList) {
        System.out.println("addOrder");
        System.out.println("customer::" + customer);
        System.out.println("menuList::" + menuList);
        Order order = new Order();
        order.setCustomer(customer);
        int i = orderService.addOrder(order, menuList);
        if (i == 0) {
            return "fail";
        } else {
            return "success";
        }
    }

    @PostMapping("/doneOrder")
    public String doneOrder(@RequestParam("orderID") Integer orderID) {
        int res = orderService.setOrderDone(orderID);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("orderID") Integer orderID) {
        int res = orderService.deleteOrder(orderID);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/setOrderCook")
    public String setOrderCook(@RequestParam("orderID") Integer orderID, @RequestParam("cookName") String cookName) {
        Order order = new Order();
        order.setId(orderID);
        order.setCook(cookName);
        int res = orderService.setOrderCook(order);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}

