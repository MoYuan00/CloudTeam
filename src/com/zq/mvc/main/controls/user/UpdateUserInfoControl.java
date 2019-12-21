package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "user/")
public class UpdateUserInfoControl {

    @Autowired
    ServiceUserManager serviceUserManager;

    @Autowired
    ResponseBean responseBean;

    @RequestMapping(path = "updateUserInfoByUserId")
    @ResponseBody
    private ResponseBean exec(@RequestBody User user){
        serviceUserManager.updateUserByUser_id(user);

        return responseBean;
    }




}
