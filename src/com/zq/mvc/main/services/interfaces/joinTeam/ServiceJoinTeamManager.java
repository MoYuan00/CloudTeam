package com.zq.mvc.main.services.interfaces.joinTeam;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.JoinTeam;

public interface ServiceJoinTeamManager {

    /**
     *  将用户从团队中移除, 不会删除任务消息和当前执行的任务(会将当前执行的任务变为无人执行)
     * @param userId
     * @param teamId
     * @return
     */
    ResponseBean removeUserJoinTeam(Integer userId, Integer teamId);

    /**
     * 加入一个团队
     * @param joinTeam
     * @return
     */
    ResponseBean joinTeam(JoinTeam joinTeam);

    /**
     *  通过成员名加入团队
     * @param userName
     * @param teamId
     * @return
     */
    ResponseBean joinTeamByName(String userName, Integer teamId);

}
