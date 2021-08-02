package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import po.User;
import po.UserRead;
import service.LoginService;
import service.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody User user) {
        System.out.println("***********login***********");
        System.out.println(user);
        HashMap<String, Object> map = new HashMap<>();
        String sign = loginService.checkLogin(user.getUsername(), user.getPassword());
        User realUser = userService.getUserByUsername(user.getUsername());
        if (sign != null && realUser.getActive() != 0) {
            map.put("msg", "success");
            map.put("username", realUser.getUsername());
            map.put("rid", realUser.getRid());
            map.put("token", sign);
        } else {
            map.put("msg", "fail");
            map.put("token", null);
        }
        return map;
    }

    @GetMapping("/cookList")
    public List<String> getCookList() {
        System.out.println("获取厨师列表");
        return userService.getCookNameList();
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null
        ||user.getUsername().equals("")||user.getPassword().equals("")) {
            return "fail";
        }
        user.setRid(3);
        int res = userService.addUser(user);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/user/userList")
    public List<UserRead> getUserReadList() {
        return userService.getUserReadList();
    }

    @PostMapping("/user/updateUser")
    public String updateUser(@RequestBody User user) {
        System.out.println("updateUser::");
        System.out.println(user);
        int res = userService.updateUser(user);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/user/deleteUser")
    public String deleteUser(@RequestParam("username") String username) {
        int res = userService.deleteUser(username);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/user/addUser")
    public String addUser(@RequestBody User user) {
        System.out.println("addUser");
        System.out.println(user);
        int res = userService.addUser(user);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/user/freezeUser")
    public String freezeUser(@RequestParam("username") String username) {
        int res = userService.freezeUser(username);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/user/releaseUser")
    public String releaseUser(@RequestParam("username") String username) {
        int res = userService.releaseUser(username);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/user/updatePasswd")
    public String updatePasswd(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int res = userService.updateUser(user);
        if (res != 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
