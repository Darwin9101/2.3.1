package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void insertUser(String name, String email, String password);
    void updateUser(String name, String email, String password, String nameOriginal);
    void deleteUser(String email);
    List<User> getUsers();
}
