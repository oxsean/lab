package cn.lvu.service.impl;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import cn.lvu.service.UserService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() throws Exception {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 1; i++) {
            String name = df.getFirstName() + " " + df.getLastName();
            System.out.println(name);
            User user = new User();
            user.setName(df.getFirstName().toLowerCase());
            user.setViewName(name);
            user.setRoles(new ArrayList<Role>());
            for (int j = 0; j < 2; j++) {
                Role role = new Role();
                role.setName(df.getFirstName());
                role.setDescription(df.getAddress());
                role.setUser(user);
                user.getRoles().add(role);
            }
            userService.saveUser(user);
        }
    }
}
