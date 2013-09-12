package cn.lvu.service.impl;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import cn.lvu.service.UserService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public User getUser(Integer userId) {
        return (User) getSession().get(User.class, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(Integer roleId) {
        return (Role) getSession().get(Role.class, roleId);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        DataFactory df = new DataFactory();
        Role role = new Role();
        role.setName(df.getFirstName());
        role.setDescription(df.getAddress());
        getSession().saveOrUpdate(role);
        List<Role> roles = user.getRoles();
        roles.add(role);
        getSession().saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional
    public void removeRole(Integer id) {
        User user=getUser(9);
        Role role=(Role)getSession().get(Role.class, id);
        user.getRoles().remove(role);
        //getSession().delete(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findRole(){
        //Object aa=getSession().createSQLQuery("select * from t_role").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        //System.out.println(aa);
        //User user=(User)getSession().createQuery("select distinct u from User u left join u.roles where u.id=3").setCacheable(true).uniqueResult();
        //User user=(User)getSession().createCriteria(User.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).addOrder(Order.desc("id")).list().get(0);
        //System.out.println(user.getRoles().size());
        return Collections.emptyList();
    }
}
