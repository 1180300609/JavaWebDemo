package dao;

import po.User;
import po.UserRead;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username);

    List<User> getCookList();

    int addUser(User user);

    int deleteUser(String username);

    List<UserRead> getUserReadList();

    int updateUser(User user);

    int freezeUser(String username);

    int releaseUser(String username);
}
