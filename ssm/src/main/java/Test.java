import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.MenuDao;
import dao.OrderDao;
import dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import po.Menu;
import po.Order;
import po.User;
import po.UserRead;
import service.MenuService;
import service.OrderService;
import service.UserService;
import utils.JwtUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        OrderService dao = classPathXmlApplicationContext.getBean(OrderService.class);
        Order orderByID = dao.getOrderByID(9);
        System.out.println(orderByID);
    }
}
