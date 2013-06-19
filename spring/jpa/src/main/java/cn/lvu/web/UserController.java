package cn.lvu.web;

import cn.lvu.model.User;
import cn.lvu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-4
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/z")
    @ResponseBody
    public String indexz() throws Exception {
        return userService.getUser(3).getRoles().size() + "";
    }

    @RequestMapping(value = "/x")
    @ResponseBody
    public String indexx(@RequestParam int id) throws Exception {
        userService.removeRole(id);
        return "";
    }

    @RequestMapping(value = "/a")
    @ResponseBody
    public String indexa() throws Exception {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userService.getUser(41);
        }
        return "" + (System.currentTimeMillis() - t1);
    }

    @RequestMapping(value = "/b")
    @ResponseBody
    public String indexb() throws Exception {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userService.getUser1(41);
        }
        return "" + (System.currentTimeMillis() - t1);
    }

    @RequestMapping(value = "/c")
    @ResponseBody
    public String indexc() throws Exception {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userService.getUserCached(41);
        }
        return "" + (System.currentTimeMillis() - t1);
    }

    @RequestMapping(value = "/d")
    @ResponseBody
    public String indexd() throws Exception {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userService.getUserCached1(41);
        }
        return "" + (System.currentTimeMillis() - t1);
    }

    @RequestMapping(value = "/e")
    @ResponseBody
    public String indexe() throws Exception {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            userService.getUser2(41);
        }
        return "" + (System.currentTimeMillis() - t1);
    }

    @RequestMapping(value = "/f")
    @ResponseBody
    public String indexf() throws Exception {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User user = userService.getUser3(41);
            //System.out.println(user.getName());
        }
        return "" + (System.currentTimeMillis() - t1);
    }
}
