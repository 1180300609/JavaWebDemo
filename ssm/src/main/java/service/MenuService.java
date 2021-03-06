package service;

import org.springframework.transaction.annotation.Transactional;
import po.Menu;

import java.util.List;

@Transactional
public interface MenuService {
    List<Menu> getMenuList();

    Menu getMenuByID(Integer id);

    Menu getMenuByName(String name);

    int deleteMenuByID(Integer id);

    int updateMenu(Menu menu);

    int addMenu(Menu menu);

    List<Menu> searchMenu(String name);
}
