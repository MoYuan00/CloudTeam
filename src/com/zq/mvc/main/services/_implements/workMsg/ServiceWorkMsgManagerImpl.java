package com.zq.mvc.main.services._implements.workMsg;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.mappers.workMsg.WorkMsgManagerMapper;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.pojo.WorkMsg;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import com.zq.mvc.main.services.interfaces.workMsg.ServiceWorkMsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceWorkMsgManagerImpl implements ServiceWorkMsgManager {
    @Autowired
    ResponseBean responseBean;

    @Autowired
    WorkMsgManagerMapper workMsgManagerMapper;

    @Autowired
    ServiceUserManager serviceUserManager;

    @Override
    public ResponseBean appendWorkMsg(WorkMsg workMsg) {
        // 添加一条讨论信息
        int re = workMsgManagerMapper.appendWorkMsg(workMsg);
        if(re > 0){// 发布成功
            // 当发布成功后，查询发布的用户的信息，一起返回
            serviceUserManager.getUserByUser_id(workMsg.getReport_user_id());
            // 从返回结果中获取查询出的发布者信息
            User user = (User) responseBean.getData().get("user");
            workMsg.setReportUser(user);// 设置发布者
            responseBean.removeAllData();// 清空返回信息
            // 设置返回信息
            return responseBean
                    .addData("workMsg", workMsg)
                    .setMessage("发布任务消息成功！").setType("success");
        }
        // 返回错误信息
        return responseBean.setErrNo(ErrNo.appendWorkMsgFail);
    }

    @Transactional
    @Override
    public ResponseBean deleteAllWorkMsg(Integer workId) {
        workMsgManagerMapper.deleteAllWorkMsgByWork_id(workId);
        return responseBean.setMessage("删除任务的消息成功！");
    }

    @Override
    public ResponseBean deleteUserWorkMsg(Integer workId, Integer userId) {
        int re = workMsgManagerMapper.deleteUserWorkMsg(workId, userId);
        return responseBean.setMessage("删除消息成功");
    }

    @Override
    public ResponseBean updateReport_user_id(Integer workId, Integer userId) {
        int re = workMsgManagerMapper.updateWorkMsgReport_user_id(workId, userId);
        if(re > 0){
            return responseBean;
        }
        return responseBean.setErrNo(ErrNo.updateWorkMsgFail);
    }


}
