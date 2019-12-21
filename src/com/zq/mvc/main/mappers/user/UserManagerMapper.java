package com.zq.mvc.main.mappers.user;

import com.zq.mvc.main.pojo.User;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface UserManagerMapper {
    /**
     * 获取所有的用户信息
     * @return
     */
    public List<User> getAllUser();

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUserByUserId(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int appendUser(User user);

    /**
     * 删除一个用户
     * @param user_id
     * @return
     */
    public int removeUserByUser_id(Integer user_id);


    /**
     *  通过user_id获取用户信息
     * @param user_id
     * @return
     */
    User getUserByUser_id(Integer user_id);

    /**
     *  更新用户的信息，不更新密码
     * @param user
     * @return
     */
    int updateUserInfoByUser_id(User user);

    /**
     *  通过team_id获取用户信息----获取一个团队里的所有用户
     * @param team_id
     * @return
     */
    List<User> getJoinUserByTeam_id(Integer team_id);

    /**
     *  通过team_id获取团队的创建者的信息
     * @param team_id
     * @return
     */
    User getCreateUserByTeam_id(Integer team_id);


    User getUserByName(String name);

}
