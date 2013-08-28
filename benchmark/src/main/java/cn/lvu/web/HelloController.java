package cn.lvu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-4
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/springa")
    public String springa() throws Exception {
        return "hello";
    }

    @RequestMapping(value = "/springb/{id}")
    public String springb(@PathVariable("id") int id) throws Exception {
        return "hello";
    }
}
