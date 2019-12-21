package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.user.ServiceLogin;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class GetLoginUserInfoControl {

    static Logger log = Logger.getLogger(LoginControl.class);

    @Autowired
    ServiceLogin serviceLogin;

    @Autowired
    ServiceUserManager serviceUserManager;

    @Autowired
    ResponseBean responseBean;

    @ResponseBody
    @RequestMapping(path = "/getLoginUser")
    public ResponseBean exec
            (@SessionAttribute(name = "user", required = false) User user,
                             HttpSession session){
        log.info("exec!");
        if(user == null){
            log.info("当前没有登录用户!");
            return responseBean.setErrNo(ErrNo.noLoginUser);
        }else{
            // 实时更新用户
            serviceUserManager.getUserByUser_id(user.getUser_id());
            user = (User) responseBean.getData().get("user");
            session.setAttribute("user", user);
        }
        log.info("当前登录用户为:" + user);
        responseBean.addData("user", user);
        return responseBean;
    }


}
