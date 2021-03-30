package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void add (User user);
    User getUserById(Long id);
    User getUserByName(String name);
    void update(User user);
    void deleteById(Long id);

}
