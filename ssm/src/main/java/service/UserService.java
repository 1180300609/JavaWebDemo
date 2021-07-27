package service;

import po.User;
import po.UserRead;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);

    List<String> getCookNameList();

    int addUser(User user);

    List<UserRead> getUserReadList();

    int updateUser(User user);

    int deleteUser(String username);

    int freezeUser(String username);

    int releaseUser(String username);
}
