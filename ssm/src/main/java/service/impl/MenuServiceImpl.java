package service.impl;

import dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Menu;
import service.MenuService;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuList() {
        return menuDao.getMenuList();
    }

    @Override
    public Menu getMenuByID(Integer id) {
        if (id == null) {
            return null;
        }else {
            return menuDao.selectMenuByID(id);
        }
    }

    @Override
    public Menu getMenuByName(String name) {
        if (name == null) {
            return null;
        }else {
            return menuDao.selectMenuByName(name);
        }
    }

    @Override
    public int deleteMenuByID(Integer id) {
        if (id == null) {
            return 0;
        }else {
            return menuDao.deleteMenuByID(id);
        }
    }

    @Override
    public int addMenu(Menu menu) {
        if (menu.getName() == null || menu.getName().equals("")|| menu.getPrice()==0 ) {
            return 0;
        }
        return menuDao.insertMenu(menu);
    }

    @Override
    public List<Menu> searchMenu(String name) {
        if (name == null) {
            return null;
        } else {
            return menuDao.searchMenu(name);
        }
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }
}
