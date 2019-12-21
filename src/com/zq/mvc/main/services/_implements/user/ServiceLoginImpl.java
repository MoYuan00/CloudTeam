package com.zq.mvc.main.services._implements.user;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.mappers.user.LoginMapper;
import com.zq.mvc.main.services.interfaces.user.ServiceLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLoginImpl implements ServiceLogin {

    static Logger logger = Logger.getLogger(ServiceLoginImpl.class);
    @Autowired
    LoginMapper mapperLogin;

    @Autowired
    ResponseBean responseBean;

    @Override
    public ResponseBean loginServer(
            String name, String password) {
        logger.info("登录：" + name + "|" + password);
        User user = mapperLogin.loginByNameAndPassword(name, password);
        if(user != null) {
            user.setPassword(null);// 不返回密码
            responseBean.addData("user", user);
        }else
            responseBean.setErrNo(ErrNo.loginFail).setType("error");
        logger.info("登录结果：" + responseBean);
        return responseBean;
    }
}
