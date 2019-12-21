package com.zq.mvc.main.services._implements.work;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.mappers.work.WorkManagerMapper;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.pojo.Work;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import com.zq.mvc.main.services.interfaces.workMsg.ServiceWorkMsgManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceWorkManagerImpl implements ServiceWorkManager {

    @Autowired
    WorkManagerMapper workManagerMapper;

    @Autowired
    ServiceWorkMsgManager serviceWorkMsgManager;

    static Logger logger = Logger.getLogger(ServiceWorkManagerImpl.class);

    @Autowired
    ResponseBean responseBean;

    @Override
    public ResponseBean getCreateWorks(Integer user_id, Integer team_id) {
        return responseBean.addData("createWorks", workManagerMapper.getCreateWorksByTeam_idAndUser_id(team_id, user_id));
    }

    @Override
    public ResponseBean getExecWorks(Integer user_id, Integer team_id) {
        responseBean.addData("execWorks", workManagerMapper.getExecWorksByTeam_idAndUser_id(team_id, user_id));
        logger.info("getExecWorks data" + responseBean.getData());
        return responseBean;
    }

    @Transactional
    @Override
    public ResponseBean getTeamWorks(Integer user_id, Integer team_id) {
        List<Work> works = new ArrayList<>();
        // 获取团队中的某个用户具有的（执行的，和发布的）所有任务的信息
        works.addAll(workManagerMapper.getWorksByTeam_idAndUser_id(team_id, user_id));
        // 返回获取的信息
        return responseBean.addData("works", works);
    }

    @Transactional
    @Override
    public ResponseBean deleteWork(Integer workId) {
        // 先删除任务的消息
        serviceWorkMsgManager.deleteAllWorkMsg(workId);
        if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())){// 如果任务消息删除成功
            // 删除任务
            int re = workManagerMapper.deleteWorkByWorkId(workId);
            if(re > 0)
                return responseBean.setMessage("删除成功！");
            else
                return responseBean.setMessage("删除出错！").setType("error");
        }else{
            // 删除失败
            return responseBean.setErrNo(ErrNo.deleteWorkFail);
        }
    }

    @Autowired
    ServiceUserManager serviceUserManager;

    @Transactional
    @Override
    public ResponseBean appendWork(Work work) {
        // 掉用 dao 添加一个工作
        int re = workManagerMapper.appendWork(work);
        // 添加团队后，将团队的发布者和执行者信息查询出来，打包返回
        if(re > 0){
            // 调用查询用户服务，获取发布者信息
            responseBean = serviceUserManager.getUserByUser_id(work.getPublish_user_id());
            // 获取查询结果，并设置
            User user = (User) responseBean.getData().get("user");
            work.setPublishUser(user);
            responseBean.removeAllData();// 清空查询结果信息
            if(work.getExec_user_id() != null){// 如果有执行者
                // 调用查询用户服务，获取执行者信息
                responseBean = serviceUserManager.getUserByUser_id(work.getPublish_user_id());
                User exec_user = (User) responseBean.getData().get("user");
                work.setExecUser(exec_user);
                responseBean.removeAllData();
            }
            // 返回创建成功信息
            return responseBean.addData("work", work).setMessage("创建任务成功！");
        }
        // 添加失败，获取发布者信息，执行者信息失败都将导致事务回滚
        // 返回创建失败信息
        return responseBean.setErrNo(ErrNo.appendWorkFail);
    }

    @Override
    public ResponseBean updateWorkStatusId(Integer workId, Integer workStatusId) {
        // 调用 dao 更新任务状态id
        int re = workManagerMapper.updateWorkStatus(workId, workStatusId);
        if(re > 0){// 更新成功
            return responseBean.setMessage("更新状态成功！").setType("success");
        }
        // 修改失败，返回错误信息
        return responseBean.setErrNo(ErrNo.updateWorkFail);
    }

    @Override
    public ResponseBean updateExecUserId(Integer workId, Integer userId) {
        // 调用 dao 更新执行者id
        int re = workManagerMapper.updateExecUser(workId, userId);
        if(re > 0){// 如果修改成功
            // 返回修改成功信息
            return responseBean.setMessage("更新执行者成功！").setType("success");
        }
        // 修改失败，返回错误信息
        return responseBean.setErrNo(ErrNo.updateWorkFail);
    }

    @Override
    public ResponseBean updateName(Integer workId, String name) {
        // 调用 dao 更新任务名 id
        int re = workManagerMapper.updateName(workId, name);
        if(re > 0){// 如果修改成功
            return responseBean.setMessage("更新任务名成功！").setType("success");
        }
        // 修改失败，返回错误信息
        return responseBean.setErrNo(ErrNo.updateWorkFail);
    }

    @Override
    public ResponseBean updatePublish_user_id(Integer workId, Integer userId) {
        int re = workManagerMapper.updatePublish_user_id(workId, userId);
        if(re > 0){
            return responseBean;
        }
        return responseBean.setErrNo(ErrNo.updateWorkFail);
    }
}
