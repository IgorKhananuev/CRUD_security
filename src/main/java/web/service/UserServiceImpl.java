package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    UserDao getUserDao(){
        return userDao;
    }


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);

    }
}
