package com.zq.mvc.main.services._implements.joinTeam;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.mappers.joinTeam.JoinTeamManagerMapper;
import com.zq.mvc.main.pojo.JoinTeam;
import com.zq.mvc.main.pojo.Team;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.pojo.Work;
import com.zq.mvc.main.services.interfaces.joinTeam.ServiceJoinTeamManager;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import com.zq.mvc.main.services.interfaces.workMsg.ServiceWorkMsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ServiceJoinTeamManagerImpl implements ServiceJoinTeamManager {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    JoinTeamManagerMapper joinTeamManagerMapper;

    @Autowired
    ServiceWorkManager serviceWorkManager;

    @Autowired
    ServiceTeamManager serviceTeamManager;

    @Autowired
    ServiceWorkMsgManager serviceWorkMsgManager;


    @Override
    public ResponseBean removeUserJoinTeam(Integer userId, Integer teamId) {
        // 获取到要移除的角色的团队中的所有任务
        serviceWorkManager.getTeamWorks(userId, teamId);
        // 获取团队的基本信息
        serviceTeamManager.getTeamSomeInfo(teamId);
        if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())
            && responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())
        ){
            Team team = (Team) responseBean.getData().get("team");
            List<Work> works = (List<Work>) responseBean.getData().get("works");
            for(Work work : works){
                // 将所有此角色执行的任务设置为null(无人执行)
                if(work.getExec_user_id().equals(userId))
                    serviceWorkManager.updateExecUserId(work.getWork_id(), null);
                // 将所有此角色创建的任务，全部移交给队长
                if(work.getPublish_user_id().equals(userId))
                    serviceWorkManager.updatePublish_user_id(work.getWork_id(), team.getCaptain_user_id());
                // 删除用户发送的所有信息
                serviceWorkMsgManager.deleteUserWorkMsg(work.getWork_id(), userId);
            }
            // 删除完相关信息后，删除队员
            int re = joinTeamManagerMapper.removeJoinTeam(teamId, userId);
            if(re > 0){
                return responseBean.setMessage("移除成员成功！").setType("success");
            }
            return responseBean.setErrNo(ErrNo.deleteJoinTeamFail);
        }else{
            return responseBean;
        }
    }

    @Override
    public ResponseBean joinTeam(JoinTeam joinTeam) {
        int re = joinTeamManagerMapper.joinTeam(joinTeam);
        if(re > 0){
            return responseBean.setMessage("加入团队成功!").setType("success");
        }
        return responseBean.setErrNo(ErrNo.appendJoinTeamFail).setType("error");
    }

    @Autowired
    ServiceUserManager serviceUserManager;

    @Override
    public ResponseBean joinTeamByName(String userName, Integer teamId) {
        serviceUserManager.getUserByName(userName);
        // 查询队长信息
        if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())) {// 如果存在用户
            User user = (User) responseBean.getData().get("user");
            // 获取团队信息
            serviceTeamManager.getTeamAllInfo(user.getUser_id(), teamId);
            if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())) {
                Team team = (Team) responseBean.getData().get("teamInfo");
                // 如果当前用户不是团队的队长
                if(!team.getCaptain_user_id().equals(user.getUser_id())) {
                    // 如果此用户当前没有加入此团队
                    if(team.getJoinUserList() != null && !team.getJoinUserList().contains(user)){
                        // 加入队员
                        JoinTeam joinTeam = new JoinTeam();
                        joinTeam.setUser_id(user.getUser_id());
                        joinTeam.setTeam_id(teamId);
                        joinTeam.setJoin_time(Calendar.getInstance().getTime());
                        int re = joinTeamManagerMapper.joinTeam(joinTeam);
                        if (re > 0)
                            return responseBean.addData("user", user)
                                    .setMessage("加入团队成功!")
                                    .setType("success");
                        return  responseBean.setMessage("加入团队失败!").setType("error");
                    }
                    return responseBean.removeAllData().setType("warning").setMessage("此用户已经加入团队，请刷新重试！");
                }
                return responseBean.setMessage("你是团队的队长，不需要加入!").setType("warning");
            }
        }
        return responseBean.setErrNo(ErrNo.appendJoinTeamFail).setMessage("加入团队失败！").setType("error");
    }
}
