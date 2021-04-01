package web.service;

import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }
}
