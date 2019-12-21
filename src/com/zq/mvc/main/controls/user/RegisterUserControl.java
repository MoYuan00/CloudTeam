package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterUserControl {
    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceUserManager serviceUserManager;

    /**
     * 注册成功后切换到登录状态（直接将注册的用户添加到session）
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "registerUser")
    ResponseBean exec(@RequestBody User user, HttpSession session){
        serviceUserManager.registerUser(user);
        if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())){
            session.setAttribute("user", user);
        }
        return responseBean;
    }


}
