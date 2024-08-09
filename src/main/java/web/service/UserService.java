package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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
    public void updateUser(String name, String email, String password, String nameOriginal) {
        userDao.updateUser(name, email, password, nameOriginal);
    }

    @Transactional
    public void deleteUser(String email) {
        userDao.deleteUser(email);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }
}
