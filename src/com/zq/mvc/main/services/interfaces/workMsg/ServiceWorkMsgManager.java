package com.zq.mvc.main.services.interfaces.workMsg;


import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.WorkMsg;
import org.apache.ibatis.annotations.Param;

public interface ServiceWorkMsgManager {


    ResponseBean appendWorkMsg(WorkMsg workMsg);

    /**
     *  删除一个工作的所有消息
     * @param workId
     * @return
     */
    ResponseBean deleteAllWorkMsg(Integer workId);


    /**
     *  删除某个用户在某个工作下的所有信息
     * @param workId
     * @param userId
     * @return
     */
    ResponseBean deleteUserWorkMsg(
             Integer workId,
             Integer userId);

    /**
     * 更新消息的发布者
     * @param workId
     * @param userId
     * @return
     */
    ResponseBean updateReport_user_id(Integer workId, Integer userId);

}
