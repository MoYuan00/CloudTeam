package com.zq.mvc.main.services._implements.team;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.mappers.team.TeamManagerMapper;
import com.zq.mvc.main.pojo.JoinTeam;
import com.zq.mvc.main.pojo.Team;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.pojo.Work;
import com.zq.mvc.main.services.interfaces.joinTeam.ServiceJoinTeamManager;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import com.zq.mvc.main.services.interfaces.workMsg.ServiceWorkMsgManager;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ServiceTeamManagerImpl implements ServiceTeamManager {

    @Autowired
    TeamManagerMapper teamManagerMapper;

    static Logger logger = Logger.getLogger(ServiceTeamManagerImpl.class);

    @Autowired
    ResponseBean responseBean;

    @Override
    public ResponseBean getAllTeamService() {
        responseBean.addData("teams", teamManagerMapper.getAllTeam());
        logger.info(responseBean);
        return responseBean;
    }

    @Override
    public ResponseBean updateTeamService(Team team) {
        logger.info(team);
        int re = teamManagerMapper.updateTeam(team);
        if(re > 0){
            return responseBean.setErrNo(ErrNo.noErr);
        }
        return responseBean.setErrNo(ErrNo.updateTeamFail);
    }

    @Autowired
    ServiceUserManager serviceUserManager;

    @Override
    public ResponseBean appendTeamService(Integer user_id, Team team) {
        if(team == null) return responseBean.setErrNo(ErrNo.appendTeamFail);
        if (team.getTeam_build_time() == null)
            team.setTeam_build_time(Calendar.getInstance().getTime());
        logger.info(user_id + "|" + team);
        int re = teamManagerMapper.appendTeam(user_id, team);
        // 查询队长信息打包返回
        serviceUserManager.getUserByUser_id(user_id);
        User user = (User) responseBean.getData().get("user");
        team.setCaptainUser(user);
        responseBean.removeAllData();
        if(re > 0){
            return responseBean.addData("team", team);
        }
        return responseBean.setErrNo(ErrNo.appendTeamFail);
    }

    @Override
    public ResponseBean deleteTeamService(Integer team_id) {
        logger.info(team_id);
        int re = teamManagerMapper.deleteTeamByTeam_id(team_id);
        if(re > 0)
            return responseBean.setErrNo(ErrNo.noErr);
        return responseBean.setErrNo(ErrNo.deleteTeamFail);
    }

    @Override
    public ResponseBean getCreateTeamByUser_id(Integer user_id) {

        return responseBean.addData("createTeams", teamManagerMapper.getCreateTeamByUser_id(user_id));
    }

    @Override
    public ResponseBean getJoinTeamByUser_id(Integer user_id) {
        return responseBean.addData("joinTeams", teamManagerMapper.getJoinTeamByUser_id(user_id));
    }

    @Transactional
    @Override
    public ResponseBean getTeamByUser_id(Integer user_id) {
        responseBean.addData("createTeams", teamManagerMapper.getCreateTeamByUser_id(user_id));
        responseBean.addData("joinTeams", teamManagerMapper.getJoinTeamByUser_id(user_id));
        return responseBean;
    }

    @Override
    public ResponseBean getTeamAllInfo(Integer user_id, Integer team_id) {
        return responseBean.addData("teamInfo", teamManagerMapper.getTeamAllInfo(team_id));
    }

    @Override
    public ResponseBean getTeamSomeInfo(Integer team_id) {
        return responseBean.addData("team", teamManagerMapper.getTeamSomeInfo(team_id));
    }

    @Override
    public ResponseBean updateTeamSomeInfo(Team team) {
        logger.info("要更新的信息为: " + team);
        int re = teamManagerMapper.updateTeamSomeInfo(team);
        if(re > 0){
            return responseBean.setMessage("更新团队信息成功！");
        }
        return responseBean.setErrNo(ErrNo.updateTeamFail);
    }

    @Autowired
    ServiceWorkManager serviceWorkManager;

    @Autowired
    ServiceJoinTeamManager serviceJoinTeamManager;

    @Override
    public ResponseBean deleteTeam(Integer userId, Integer teamId) {
        serviceWorkManager.getTeamWorks(userId, teamId);
        if(responseBean.getErrNo().equals(ErrNo.noErr.getErrNo())) {
            List<Work> workList = (List<Work>) responseBean.getData().get("works");
            try{
                for (Work work : workList){// 删除团队中的所有任务
                    serviceWorkManager.deleteWork(work.getWork_id());
                }
                // 获取团队的队员信息
                Team team = teamManagerMapper.getTeamAllInfo(teamId);
                for(User user : team.getJoinUserList()){
                    // 踢出所有队员
                    serviceJoinTeamManager.removeUserJoinTeam(user.getUser_id(), teamId);
                }
                int re = teamManagerMapper.deleteTeamByTeam_id(teamId);// 最后删除团队
                if(re > 0){
                    return responseBean.setMessage("删除团队成功!").setType("success");
                }
                return responseBean.setErrNo(ErrNo.deleteTeamFail).setMessage("删除团队失败！").setType("error");
            }catch (Exception e){
                e.printStackTrace();
                return responseBean.removeAllData().setMessage("删除团队的任务失败！").setType("error");
            }
        }
        return responseBean.setErrNo(ErrNo.deleteTeamFail).setMessage("获取团队信息失败！").setType("error");
    }


}
