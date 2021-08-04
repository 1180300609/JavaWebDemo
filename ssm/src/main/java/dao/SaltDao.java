package dao;

import po.Salt;

public interface SaltDao {
    int addSalt(Salt salt);

    int updateSalt(Salt salt);

    String getSalt(String username);
}
