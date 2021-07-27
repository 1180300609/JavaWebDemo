package dao;

import po.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getMenuList();

    Menu selectMenuByID(Integer id);

    Menu selectMenuByName(String name);

    int deleteMenuByID(Integer id);

    int updateMenu(Menu menu);

    int insertMenu(Menu menu);

    List<Menu> searchMenu(String name);
}
