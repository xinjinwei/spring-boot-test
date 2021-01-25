package com.xboot.xtest.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xboot.repository.UserRepository;
import com.xboot.repository.model.User;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/redis_session")
public class RedisSessionUserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/setSession")
    public Map<String, Object> setSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/")
    public Object getSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }


    @RequestMapping(value = "/index")
    public String index (HttpServletRequest request){
        String msg="index content";
        Object user= request.getSession().getAttribute("user");
        if (user==null){
            msg="please login first！";
        }
        return msg;
    }

    @RequestMapping(value = "/login")
    public String login (HttpServletRequest request,String userName,String password){
        String msg="logon failure!";
        User user= userRepository.findByUserName(userName);
        if (user!=null && user.getPassWord().equals(password)){
            request.getSession().setAttribute("user",user);
            msg="login successful!";
        }
        return msg;
    }

    @RequestMapping(value = "/loginout")
    public String loginout (HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "loginout successful!";
    }

}