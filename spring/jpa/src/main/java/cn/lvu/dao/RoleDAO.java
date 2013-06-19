package cn.lvu.dao;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-3-18
 */
public interface RoleDAO extends JpaRepository<Role, Integer>, QueryDslPredicateExecutor<Role> {
}
