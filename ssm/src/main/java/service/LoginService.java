package service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginService {
    String checkLogin(String username, String password);
}
