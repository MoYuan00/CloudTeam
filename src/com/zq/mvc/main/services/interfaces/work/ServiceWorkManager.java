package com.zq.mvc.main.services.interfaces.work;


import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.Work;

public interface ServiceWorkManager {

    ResponseBean getCreateWorks(Integer user_id, Integer team_id);

    ResponseBean getExecWorks(Integer user_id, Integer team_id);

    /**
     *  通过user_id获取用户在团队中所有可见的任务
     * @param user_id
     * @param team_id
     * @return
     */
    ResponseBean getTeamWorks(Integer user_id, Integer team_id);

    /**
     * 删除一个任务
     * @param workId
     * @return
     */
    ResponseBean deleteWork(Integer workId);


    /**
     *  添加一个任务
     * @param work
     * @return
     */
    ResponseBean appendWork(Work work);

    /**
     * 更新一个任务的任务状态
     * @param workId
     * @param workStatusId
     * @return
     */
    ResponseBean updateWorkStatusId(Integer workId, Integer workStatusId);

    /**
     * 更新任务的执行者
     * @param workId
     * @param userId
     * @return
     */
    ResponseBean updateExecUserId(Integer workId, Integer userId);

    ResponseBean updateName(Integer workId, String name);

    ResponseBean updatePublish_user_id(Integer workId, Integer userId);

}
