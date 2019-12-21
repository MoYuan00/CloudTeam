package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class LogoutControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceUserManager serviceUserManager;

    @ResponseBody
    @RequestMapping(path = "/logout")
    ResponseBean exec(HttpSession session){
        session.invalidate();
        return responseBean;
    }

}
