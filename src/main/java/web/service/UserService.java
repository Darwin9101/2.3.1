package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    @Transactional
    public void insertUser(String name, String email, String password) {
        userDao.insertUser(name, email, password);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(String email) {
        userDao.deleteUser(email);
    }

    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    public User getUserByID(Long id) {
        return userDao.getUserByID(id);
    }
}
