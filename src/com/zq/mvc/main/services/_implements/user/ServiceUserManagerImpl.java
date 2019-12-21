package com.zq.mvc.main.services._implements.user;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.mappers.user.UserManagerMapper;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserManagerImpl implements ServiceUserManager {

    @Autowired
    UserManagerMapper userManagerMapper;
    static Logger logger = Logger.getLogger(ServiceUserManagerImpl.class);

    @Autowired
    ResponseBean responseBean;

    @Override
    public ResponseBean getAllUserServer(User adminUser) {
        ResponseBean responseBean = null;
        // 需要判断当前登录的用户是否是管理员，如果是才继续
        List<User> userList = userManagerMapper.getAllUser();
        responseBean.addData("users", userList);
        return responseBean;
    }

    @Override
    public ResponseBean updateUserServer(User user) {
        logger.info(user);
        int re = userManagerMapper.updateUserByUserId(user);
        logger.info(re);
        if(re > 0){
            return responseBean.setErrNo(ErrNo.noErr);
        }else{
            return responseBean.setErrNo(ErrNo.updateUserFail);
        }
    }

    @Override
    public ResponseBean appendUserServer(User user) {
        logger.info(user);
        int re = userManagerMapper.appendUser(user);
        if(re > 0){
            logger.info("成功插入。主键回填：" + user.getUser_id());
            return responseBean.addData("user", user);
        }
        return responseBean.setErrNo(ErrNo.appendUserFail);
    }

    @Override
    public ResponseBean removeUserServer(Integer user_id) {
        logger.info(user_id);
        if(user_id == null){
            return responseBean.setErrNo(ErrNo.requiredParamHasNull);
        }
        int re = userManagerMapper.removeUserByUser_id(user_id);
        if(re > 1){
            return responseBean.setErrNo(ErrNo.noErr);
        }
        return responseBean.setErrNo(ErrNo.deleteUserFail);
    }

    @Override
    public ResponseBean getUserByUser_id(Integer user_id) {
        return responseBean.addData("user", userManagerMapper.getUserByUser_id(user_id));
    }

    @Override
    public ResponseBean updateUserByUser_id(User user) {
        int re = userManagerMapper.updateUserInfoByUser_id(user);
        if(re > 0){
            return responseBean.setMessage("修改用户信息成功！").setType("success");
        }
        return responseBean.setErrNo(ErrNo.updateUserFail);
    }

    /**
     * 获取一个团队中的所有成员
     * @param team_id
     * @return
     */
    @Override
    public ResponseBean getUserByTeam_id(Integer team_id) {
        List<User> joinUsers = userManagerMapper.getJoinUserByTeam_id(team_id);
        User createUser = userManagerMapper.getCreateUserByTeam_id(team_id);
        responseBean.addData("joinUsers",joinUsers);
        responseBean.addData("createUser", createUser);
        return responseBean;
    }

    @Override
    public ResponseBean getUserByName(String name) {
        User user = userManagerMapper.getUserByName(name);
        if(user != null){
            return responseBean.addData("user", user);
        }
        return responseBean.setErrNo(ErrNo.findUserFail)
                .setType("warning").setMessage("当前用户没有找到");
    }


    @Override
    public ResponseBean registerUser(User user) {
        user.setDetail(null);
        user.setRole("User");
        int re = userManagerMapper.appendUser(user);
        if(re > 0){
            return responseBean.addData("user", user)
                    .setMessage("注册成功！").setType("success");
        }
        return responseBean.setErrNo(ErrNo.appendUserFail);
    }

    @Override
    public ResponseBean isCanUseName(String name) {
        User user = userManagerMapper.getUserByName(name);
        if(user != null && user.getUser_id() != null){
            return responseBean.addData("isCanUse", false);
        }
        return responseBean.addData("isCanUse", true);
    }

}
