package cn.lvu.service.impl;

import cn.lvu.dao.RoleDAO;
import cn.lvu.dao.UserDAO;
import cn.lvu.model.Role;
import cn.lvu.model.User;
import cn.lvu.service.UserService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-3
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    private HibernateEntityManager entityManager;

    private NamedParameterJdbcTemplate jt;

    private RowMapper<User> map = ParameterizedBeanPropertyRowMapper.newInstance(User.class);

    public void setJt(NamedParameterJdbcTemplate jt) {
        this.jt = jt;
    }

    public Cache<Integer, User> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(8, TimeUnit.SECONDS).maximumSize(100)
            .build();

    @PersistenceContext
    public void setEntityManager(HibernateEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUser(Integer userId) {
        return userDAO.findOne(userId);
    }

    @Override
    public User getUser1(Integer i) {
        try {
            return userDAO.findOne(i);
        } finally {
            entityManager.clear();
        }
    }

    @Override
    public User getUser3(Integer i) {
        try {
            return (User) entityManager.getSession().get(User.class, i);
        } finally {
            entityManager.clear();
        }
    }

    @Override
    @Transactional
    public void removeRole(Integer id) {
        Role role = roleDAO.findOne(id);
        User user = role.getUser();
        user.getRoles().remove(role);
        //roleDAO.delete(role);
    }

    @Override
    @Transactional
    public void test() {
        User user1 = getUser(1);
        //User user2 = getUser(2);
        //Role role = user1.getRoles().get(1);
        //role.setName("asdasd");
        //user1.getRoles().add(role);
        saveUser(user1);
        System.out.println(user1);
    }

    @Override
    public User getUser2(Integer i) {
        return jt.queryForObject("SELECT * FROM t_user WHERE id=:id", Collections.singletonMap("id", i), map);
    }

    @Override
    @Cacheable(value = "userCache", key = "'u-'+#userId")
    public User getUserCached(Integer userId) {
        return getUser(userId);
    }

    @Override
    public User getUserCached1(final Integer userId) {
        User user = null;
        try {
            user = cache.get(userId, new Callable<User>() {
                @Override
                public User call() throws Exception {
                    return getUser(userId);
                }
            });
        } catch (ExecutionException ignored) {
        }
        return user;
    }

    @Override
    public User getUserByName(String userName) {
        return userDAO.findByName(userName);
    }

    @Override
    public Map<Integer, User> getUsers(Collection<Integer> userIds) {
        Map<Integer, User> map = Maps.newHashMapWithExpectedSize(userIds.size());
        for (Integer userId : userIds) {
            map.put(userId, getUser(userId));
        }
        return map;
    }

    @Override
    public Page<User> findUsers(String keyword, Pageable request) {
        if (StringUtils.isEmpty(keyword)) {
            return userDAO.findAll(request);
        } else {
            keyword = "%" + keyword + "%";
            //return userDAO.findAll(user.name.like(keyword).or(user.viewName.like(keyword)), request);
            return null;
        }
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        DataFactory df = new DataFactory();
        Role role = new Role();
        role.setName(df.getFirstName());
        role.setDescription(df.getAddress());
        role.setUser(user);
        List<Role> roles = user.getRoles();
        roles.add(role);
        entityManager.getSession().merge(user);
        return user;
    }

    @Override
    @Transactional
    public void removeUser(Integer userId) {
        userDAO.delete(userId);
    }

    @Override
    public Collection<Role> getUserRoles(Integer userId) {
        return getUser(userId).getRoles();
    }
}
