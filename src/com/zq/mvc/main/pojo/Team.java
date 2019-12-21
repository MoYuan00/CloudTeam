package com.zq.mvc.main.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class Team {
    private Integer team_id;
    private Integer captain_user_id;
    private String team_name;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date team_build_time;
    private String team_detail;
    /**
     * 一对一
     */
    private User captainUser;
    /**
     * 一个用户可以加入多个团队，每个团队有多个用户加入，多对多级联
     */
    private List<User> joinUserList;
    /**
     * 一个团队中有多个任务
     */
    private List<Work> workList;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Team{");
        sb.append("team_id=").append(team_id);
        sb.append(", captain_user_id=").append(captain_user_id);
        sb.append(", team_name='").append(team_name).append('\'');
        sb.append(", team_build_time=").append(team_build_time);
        sb.append(", team_detail='").append(team_detail).append('\'');
        sb.append(", captainUser=").append(captainUser);
        sb.append(", joinUserList=").append(joinUserList);
        sb.append(", workList=").append(workList);
        sb.append('}');
        return sb.toString();
    }


    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    public User getCaptainUser() {
        return captainUser;
    }

    public void setCaptainUser(User captainUser) {
        this.captainUser = captainUser;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getCaptain_user_id() {
        return captain_user_id;
    }

    public void setCaptain_user_id(Integer captain_user_id) {
        this.captain_user_id = captain_user_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Date getTeam_build_time() {
        return team_build_time;
    }

    public void setTeam_build_time(Date team_build_time) {
        this.team_build_time = team_build_time;
    }

    public String getTeam_detail() {
        return team_detail;
    }

    public void setTeam_detail(String team_detail) {
        this.team_detail = team_detail;
    }

    public List<User> getJoinUserList() {
        return joinUserList;
    }

    public void setJoinUserList(List<User> joinUserList) {
        this.joinUserList = joinUserList;
    }
}
