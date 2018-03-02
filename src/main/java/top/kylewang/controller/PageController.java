package top.kylewang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: 页面跳转
 *
 * @author Kyle.Wang
 * 2018-02-28 10:19
 */

@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "login";
    }

}
