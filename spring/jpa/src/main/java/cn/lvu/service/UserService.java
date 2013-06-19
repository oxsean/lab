package cn.lvu.service;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-5-30
 */
public interface UserService {

    User getUser(Integer userId);

    User getUser1(Integer i);

    User getUserCached(Integer userId);

    User getUserCached1(Integer userId);

    User getUserByName(String userName);

    Map<Integer, User> getUsers(Collection<Integer> userIds);

    Page<User> findUsers(String keyword, Pageable request);

    User saveUser(User user);

    void removeUser(Integer userId);

    Collection<Role> getUserRoles(Integer userId);

    User getUser2(Integer i);

    User getUser3(Integer i);

    void test();

    void removeRole(Integer id);
}
