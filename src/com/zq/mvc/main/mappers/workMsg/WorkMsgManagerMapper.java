package com.zq.mvc.main.mappers.workMsg;


import com.zq.mvc.main.pojo.WorkMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkMsgManagerMapper {

    List<WorkMsg> getWorkMsgByWork_id(Integer work_id);

    int appendWorkMsg(WorkMsg workMsg);

    /**
     * 删除一个任务的所有消息
     * @param work_id
     * @return
     */
    int deleteAllWorkMsgByWork_id(Integer work_id);

    /**
     *  删除某个用户在某个工作下的所有信息
     * @param work_id
     * @param user_id
     * @return
     */
    int deleteUserWorkMsg(
            @Param("work_id") Integer work_id,
            @Param("user_id") Integer user_id);

    int updateWorkMsgReport_user_id(
            @Param("work_id") Integer work_id,
            @Param("report_user_id") Integer report_user_id);

}
