package service.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import po.UserRead;
import service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

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
        try {
            res = userDao.addUser(user);
        } catch (Exception e) {
            System.out.println("添加用户失败");
        }
        return res;
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
            return userDao.updateUser(user);
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
