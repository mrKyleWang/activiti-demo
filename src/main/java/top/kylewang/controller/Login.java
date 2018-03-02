package top.kylewang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kylewang.service.LoginService;

import javax.servlet.http.HttpSession;


@Controller
public class Login {
    @Autowired
    LoginService loginService;

    @RequestMapping("/loginvalidate")
    public String loginvalidate(@RequestParam("username") String username, @RequestParam("password") String pwd, HttpSession httpSession) {
        if (username == null) return "login";
        String realpwd = loginService.getpwdbyname(username);
        if (realpwd != null && pwd.equals(realpwd)) {
            httpSession.setAttribute("username", username);
            return "index";
        } else return "fail";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/showUser")
    @ResponseBody
    public String  showUser(HttpSession session){
        String username = (String) session.getAttribute("username");
        return username;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        return "login";
    }
}
