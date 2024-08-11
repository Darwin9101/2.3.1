package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void insertUser(String name, String email, String password);

    void updateUser(User user);

    void deleteUser(String email);

    List<User> getUsers();

    User getUserByID(Long id);
}
