package com.zq.mvc.main.services.interfaces.user;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.User;

public interface ServiceUserManager {
    /**
     * 获取所有的用户信息
     * @param adminUser
     * @return
     */
    public ResponseBean getAllUserServer(User adminUser);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public ResponseBean updateUserServer(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public ResponseBean appendUserServer(User user);

    /**
     * 删除一个用户
     * @param user_id 将一个用户删除
     * @return
     */
    public ResponseBean removeUserServer(Integer user_id);


    /**
     * 通过user_id 获取用户的信息
     * @param user_id
     * @return
     */
    ResponseBean getUserByUser_id(Integer user_id);

    /**
     *  通过用户id更新用户的部分信息 - 不更新密码
     * @param user
     * @return
     */
    ResponseBean updateUserByUser_id(User user);

    /**
     * 获取一个团队里的所有成员的信息
     * @param team_id
     * @return
     */
    ResponseBean getUserByTeam_id(Integer team_id);

    /**
     * 通过用户名获取用户信息
     * @param name
     * @return
     */
    ResponseBean getUserByName(String name);

    /**
     * 注册一个用户 角色为普通用户User
     * @param user
     * @return
     */
    ResponseBean registerUser(User user);

    /**
     *  是否存在某个用户名
     * @param name
     * @return
     */
    public ResponseBean isCanUseName(String name);


}
