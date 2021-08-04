package service.impl;

import dao.SaltDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Salt;
import po.User;
import po.UserRead;
import service.UserService;
import utils.EncryptedUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SaltDao saltDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<String> getCookNameList() {
        List<User> cookList = userDao.getCookList();
        //优雅
        return cookList.stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public int addUser(User user) {
        if (user.getUsername() == null || user.getUsername().equals("")) {
            return 0;
        }
        int res = 0;
        String password = user.getPassword();
        String salt = EncryptedUtil.getSalt();
        String pbkdf2 = EncryptedUtil.getPBKDF2(password, salt);
        user.setPassword(pbkdf2);
        res = userDao.addUser(user);
        if (res != 0) {
            Salt s = new Salt();
            s.setSalt(salt);
            s.setUsername(user.getUsername());
            return saltDao.addSalt(s);
        }else {
            return res;
        }
    }

    @Override
    public List<UserRead> getUserReadList() {
        return userDao.getUserReadList();
    }

    @Override
    public int updateUser(User user) {
        if (user.getUsername() == null || user.getUsername().equals("")) {
            return 0;
        } else {
            if (user.getPassword().equals("")) {
                return userDao.updateUser(user);
            }else {
                String password = user.getPassword();
                String salt = EncryptedUtil.getSalt();
                String pbkdf2 = EncryptedUtil.getPBKDF2(password, salt);
                user.setPassword(pbkdf2);
                userDao.updateUser(user);
                Salt s = new Salt();
                s.setSalt(salt);
                s.setUsername(user.getUsername());
                return saltDao.updateSalt(s);
            }
        }
    }

    @Override
    public int deleteUser(String username) {
        if (username == null) {
            return 0;
        } else {
            return userDao.deleteUser(username);
        }
    }

    @Override
    public int freezeUser(String username) {
        if (username == null) {
            return 0;
        } else {
            return userDao.freezeUser(username);
        }
    }

    @Override
    public int releaseUser(String username) {
        if (username == null) {
            return 0;
        } else {
            return userDao.releaseUser(username);
        }
    }
}
