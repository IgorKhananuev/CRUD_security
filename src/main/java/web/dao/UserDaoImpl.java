package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery(" from User ", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public User getUserById(Long id) {
        return (User) entityManager.createQuery("from User where id =:longID")
                .setParameter("longID", id)
                .getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        return (User) entityManager.createQuery("from User where name =: name")
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);

    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.createQuery("from User where id =:id")
                .setParameter("id", id)
                .getSingleResult());
    }
}
