package com.zq.mvc.main.mappers.team;


import com.zq.mvc.main.pojo.JoinTeam;
import com.zq.mvc.main.pojo.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamManagerMapper {

    public List<Team> getAllTeam();

    public int updateTeam(Team team);

    public int appendTeam(@Param("user_id") Integer user_id, @Param("team") Team team);

    public int deleteTeamByTeam_id(Integer team_id);


    /**
     *  通过用户id获取当前用户创建的团队
     * @param user_id
     * @return
     */
    public List<Team> getCreateTeamByUser_id(Integer user_id);

    /**
     *  通过用户id获取当前用户加入的团队
     * @param user_id
     * @return
     */
    public List<JoinTeam> getJoinTeamByUser_id(Integer user_id);

    /**
     * 获取某个团队的全部信息 - 包括团队的用户信息
     * @param team_id
     * @return
     */
    Team getTeamAllInfo(Integer team_id);

    /**
     * 更新团队可以更新的信息，比如创建时间和创建者不可以更新
     * @param team
     * @return
     */
    int updateTeamSomeInfo(Team team);

    /**
     *  获取某个团队的部分信息，不涉及级联
     * @param team_id
     * @return
     */
    Team getTeamSomeInfo(Integer team_id);

}
