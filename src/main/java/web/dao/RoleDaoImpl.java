package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRole(Long id) {
        return entityManager.getReference(Role.class, id);
    }

    @Override
    public List<Role> listRolesByUser(Long userId) {
        List<Role> roles;
        roles = entityManager.createQuery("select u.roles from User u where u.id =: userId")
                .setParameter("userId", userId).getResultList();
        return roles;
    }
}
