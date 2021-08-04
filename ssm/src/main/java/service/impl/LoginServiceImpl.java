package service.impl;

import dao.SaltDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.User;
import service.LoginService;
import utils.EncryptedUtil;
import utils.JwtUtil;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SaltDao saltDao;

    @Override
    public String checkLogin(String username, String password) {
        if (username != null && password != null) {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                String salt = saltDao.getSalt(user.getUsername());
                String key = user.getPassword();
                boolean verify = EncryptedUtil.verify(password, salt, key);
                if (verify) {
                    return JwtUtil.sign(username);
                }
            }
        }
        return null;
    }
}
