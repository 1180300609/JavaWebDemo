package service.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import service.LoginService;
import utils.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDao userDao;

    @Override
    public String checkLogin(String username, String password) {
        if (username != null && password != null) {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return JwtUtil.sign(username);
                }
            }
        }
        return null;
    }
}
