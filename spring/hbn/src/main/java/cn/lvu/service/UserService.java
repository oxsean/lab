package cn.lvu.service;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-5-30
 */
public interface UserService {

    User getUser(Integer userId);

    User saveUser(User user);

    void removeRole(Integer id);

    @Transactional(readOnly = true)
    List<Role> findRole();
}
