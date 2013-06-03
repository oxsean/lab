package cn.lvu.service.impl;

import cn.lvu.model.Role;
import cn.lvu.model.User;
import cn.lvu.service.UserService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void testGetUser() throws Exception {

    }

    @Test
    public void testSaveUser() throws Exception {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 20; i++) {
            String name = df.getFirstName() + " " + df.getLastName();
            System.out.println(name);
            User user = new User();
            user.setName(df.getFirstName().toLowerCase());
            user.setViewName(name);
            user.setRoles(new HashSet<Role>());
            for (int j = 0; j < 5; j++) {
                Role role = new Role();
                role.setName(df.getFirstName());
                role.setDescription(df.getAddress());
                user.getRoles().add(role);
            }
            userService.saveUser(user);
        }
    }

    @Test
    public void testRemoveUser() throws Exception {
        for (int i = 1; i < 40; i++) {
            userService.removeUser(i);
        }
    }

    @Test
    public void testGetUserRoles() throws Exception {

    }
}
