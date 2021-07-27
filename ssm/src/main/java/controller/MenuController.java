package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import po.Menu;
import service.MenuService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenu() {
        List<Menu> menuList = menuService.getMenuList();
        menuList.forEach(System.out::println);
        return menuList;
    }

    @GetMapping("/deleteMenu/{id}")
    public String deleteMenu(@PathVariable Integer id) {
        int res = menuService.deleteMenuByID(id);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/selectMenu/{id}")
    public Menu getMenuByID(@PathVariable Integer id) {
        return menuService.getMenuByID(id);
    }

    @PostMapping("/updateMenu")
    public String updateMenu(@RequestBody Menu menu) {
        int res = menuService.updateMenu(menu);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/addMenu")
    public String addMenu(@RequestBody Menu menu){
        int res = menuService.addMenu(menu);
        if (res > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/searchMenu/{name}")
    public List<Menu> searchMenu(@PathVariable String name) {
        try {
            name= URLDecoder.decode(name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return menuService.searchMenu(name);
    }
}
