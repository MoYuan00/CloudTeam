package com.zq.mvc.main.services.interfaces.user;


import com.zq.mvc.main.dto.ResponseBean;

public interface ServiceLogin {
    /**
     *  通过用户名和密码登录，返回登录的用户
     * @param name
     * @param password
     * @return
     */
    ResponseBean loginServer(String name, String password);
}
