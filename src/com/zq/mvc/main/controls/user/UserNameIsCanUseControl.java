package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class UserNameIsCanUseControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceUserManager serviceUserManager;

    @ResponseBody
    @RequestMapping(path = "/isCanUseName")
    ResponseBean exec(String name){
        serviceUserManager.isCanUseName(name);
        return responseBean;
    }

}
