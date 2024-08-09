package web.dao;

import com.mysql.cj.xdevapi.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void insertUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser( String name, String email, String password, String nameOriginal) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :nameOriginal", User.class)
                .setParameter("nameOriginal", nameOriginal)
                .getResultList();

        if (!users.isEmpty()) {
            User user = users.get(0); // Если найдены пользователи, используйте первого из них
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            entityManager.merge(user);
        }

    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (!users.isEmpty()) {
            //удаляем всех пользователей с искомым email
            for (int i = 0; i < users.size(); i++) {
                entityManager.remove(users.get(i));
            }
        }
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
