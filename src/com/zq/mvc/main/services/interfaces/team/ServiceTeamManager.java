package com.zq.mvc.main.services.interfaces.team;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.Team;

import java.util.List;
import java.util.ResourceBundle;

public interface ServiceTeamManager {

    ResponseBean getAllTeamService();

    ResponseBean updateTeamService(Team team);

    ResponseBean appendTeamService(Integer user_id, Team team);

    ResponseBean deleteTeamService(Integer team_id);


    ResponseBean getCreateTeamByUser_id(Integer user_id);

    ResponseBean getJoinTeamByUser_id(Integer user_id);

    ResponseBean getTeamByUser_id(Integer user_id);

    ResponseBean getTeamAllInfo(Integer user_id, Integer team_id);

    /**
     * 获取团队的部分信息
     * @param team_id
     * @return
     */
    ResponseBean getTeamSomeInfo(Integer team_id);

    /**
     * 更新团队可以更新的信息，比如创建时间和创建者不可以更新
     * @param team
     * @return
     */
    ResponseBean updateTeamSomeInfo(Team team);

    /**
     *  删除一个团队，以及团队中的所有信息
     * @param teamId
     * @return
     */
    ResponseBean deleteTeam(Integer userId,Integer teamId);

}
