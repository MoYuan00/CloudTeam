package com.zq.mvc.main.mappers.joinTeam;


import com.zq.mvc.main.pojo.JoinTeam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JoinTeamManagerMapper {

    /**
     * 将一个用户从一个团队中删除
     * @param team_id
     * @param user_id
     * @return
     */
    int removeJoinTeam(
            @Param("team_id") Integer team_id,
            @Param("user_id") Integer user_id);

    /**
     * 加入一个团队
     * @param joinTeam
     * @return
     */
    int joinTeam(JoinTeam joinTeam);

}
