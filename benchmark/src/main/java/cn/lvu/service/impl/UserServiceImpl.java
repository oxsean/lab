package cn.lvu.service.impl;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import cn.lvu.service.UserService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.transform.ToListResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-3
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionFactory sessionFactory;

    public final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    //@Cacheable(value = "userCache", key = "'u-'+#userId")
    public User getUser(Integer userId) {
        return (User) getSession().get(User.class, userId);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        Connection con = ((SessionImplementor) getSession()).connection();
        try {
            con.setReadOnly(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataFactory df = new DataFactory();
        Role role = new Role();
        role.setName(df.getFirstName());
        role.setDescription(df.getAddress());
        role.setUser(user);
        List<Role> roles = user.getRoles();
        roles.add(role);
        getSession().saveOrUpdate(role);
        return user;
    }

    @Override
    @Transactional
    public void removeRole(Integer id) {
        Role role = (Role) getSession().get(Role.class, id);
        User user = role.getUser();
        user.getRoles().remove(role);
        //roleDAO.delete(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findRole(){
        //Object ojb=getSession().createSQLQuery("select id,name from t_role").addScalar("id", IntegerType.INSTANCE).addScalar("name").setCacheable(true).list();
        return Collections.emptyList();
        //return getSession().createSQLQuery("select * from t_role").addEntity(Role.class).setCacheable(true).list();
    }
}
