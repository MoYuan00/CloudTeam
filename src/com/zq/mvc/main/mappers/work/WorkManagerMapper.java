package com.zq.mvc.main.mappers.work;


import com.zq.mvc.main.pojo.Work;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkManagerMapper {

    /**
     * 获取一个用户在一个团队中的创建的工作
     * @param team_id
     * @param user_id
     * @return
     */
    List<Work> getCreateWorksByTeam_idAndUser_id(
            @Param("team_id") Integer team_id,
            @Param("user_id") Integer user_id);

    /**
     * 获取一个用户在一个团队中的要执行的工作
     * @param team_id
     * @param user_id
     * @return
     */
    List<Work> getExecWorksByTeam_idAndUser_id(
            @Param("team_id") Integer team_id,
            @Param("user_id") Integer user_id);

    /**
     * 获取一个用户在一个团队中的所有任务（执行任务，和创建的任务）
     * 注意：自己既可以是执行者又可以是创建者，所以（获取创建+执行会有重复）
     * @param team_id
     * @param user_id
     * @return
     */
    List<Work> getWorksByTeam_idAndUser_id(
            @Param("team_id") Integer team_id,
            @Param("user_id") Integer user_id);


    int deleteWorkByWorkId(Integer work_id);

    /**
     * 添加一个任务
     * @param work
     * @return
     */
    int appendWork(Work work);

    /**
     *  更新任务的状态信息
     * @param work_id
     * @param work_status_id
     * @return
     */
    int updateWorkStatus(
            @Param("work_id") Integer  work_id,
            @Param("work_status_id") Integer work_status_id);

    /**
     *  更新任务的执行者
     * @param work_id
     * @param exec_user_id
     * @return
     */
    int updateExecUser(
            @Param("work_id") Integer work_id,
            @Param("exec_user_id") Integer exec_user_id);

    /**
     *  更新任务的执行者
     * @param work_id
     * @param name
     * @return
     */
    int updateName(
            @Param("work_id") Integer work_id,
            @Param("name") String name);

    int updatePublish_user_id( @Param("work_id") Integer work_id,
                               @Param("publish_user_id") Integer publish_user_id);

}
