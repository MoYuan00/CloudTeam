package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.user.ServiceLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
//@SessionAttributes(names = "user")
public class LoginControl {
    static Logger log = Logger.getLogger(LoginControl.class);

    @Autowired
    ServiceLogin serviceLogin;

    @Autowired
    ResponseBean responseBean;

//    @ResponseBody
//    @RequestMapping(path = "/login", method = RequestMethod.GET)
//    public ResponseBean execGet(ModelMap mm,
//            String name, String password){
//        log.info("execGet!");
//        if(name == null || password == null){
//            return responseBean.setErrNo(ErrNo.requiredParamHasNull);
//        }
//        responseBean = serviceLogin.loginServer(name, password);
//        mm.addAttribute("user", responseBean.getData());// 添加到session
//        log.info("登录结果： " + responseBean);
//        return responseBean;
//    }


    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseBean execPost(HttpSession session,
                                 @RequestBody User user){
        ResponseBean responseBean = null;
        log.info("execPost!");
        log.info(user);
        if(user == null|| user.getName() == null || user.getPassword() == null){
            return responseBean.setErrNo(ErrNo.requiredParamHasNull);
        }
        responseBean = serviceLogin.loginServer(user.getName(), user.getPassword());
        if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())) {// 如果登录成功
            log.info("登录结果： " + responseBean);
            log.info(responseBean.getData().get("user"));
            // 添加到session
            session.setAttribute("user", responseBean.getData().get("user"));
        }
        return responseBean;
    }

//    @ResponseBody
//    @RequestMapping(path = "/loginPostJson", method = RequestMethod.POST)
//    public ResponseBean execPostJson(
//            String name,
//            String password){
//        ResponseBean responseBean = null;
//        log.info("execPostJson!");
//        log.info(name + "|" + password);
//        if(name == null|| password == null ){
//            return responseBean.setErrNo(ErrNo.requiredParamHasNull);
//        }
//        responseBean = serviceLogin.loginServer(name, password);
//        log.info("登录结果： " + responseBean);
//        return responseBean;
//    }
}
