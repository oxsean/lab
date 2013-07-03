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
    public String indexz(@RequestParam int id) throws Exception {
        return userService.getUser(id).getRoles().size() + "";
    }

    @RequestMapping(value = "/x")
    @ResponseBody
    public String indexx(@RequestParam int id) throws Exception {
        userService.removeRole(id);
        return "";
    }

    @RequestMapping(value = "/y")
    @ResponseBody
    public String indexy(@RequestParam int id) throws Exception {
        User user = userService.getUser(id);
        userService.saveUser(user);
        return "";
    }

    @RequestMapping(value = "/a")
    @ResponseBody
    public String indexa() throws Exception {
        return userService.findRole().size() + "";
    }
}
