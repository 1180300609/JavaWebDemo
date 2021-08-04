import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.MenuDao;
import dao.OrderDao;
import dao.SaltDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import po.*;
import service.MenuService;
import service.OrderService;
import service.UserService;
import utils.EncryptedUtil;
import utils.JwtUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        SaltDao dao = context.getBean(SaltDao.class);
        Salt salt = new Salt();
        salt.setUsername("test");
        salt.setSalt("13343333");
        dao.updateSalt(salt);
    }
}
