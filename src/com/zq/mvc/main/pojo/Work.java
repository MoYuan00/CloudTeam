package com.zq.mvc.main.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class Work {
    private Integer work_id;
    private String name;
    private Integer team_id;
    private Integer publish_user_id;
    private User publishUser;
    private Integer exec_user_id;
    private User execUser;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date start_time;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date end_time;
    private Integer work_status_id;
    /**
     * 一对一
     */
    private WorkStatus workStatus;
    /**
     * 一对多
     */
    private List<WorkMsg> workMsgList;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Work{");
        sb.append("work_id=").append(work_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", team_id=").append(team_id);
        sb.append(", publish_user_id=").append(publish_user_id);
        sb.append(", publishUser=").append(publishUser);
        sb.append(", exec_user_id=").append(exec_user_id);
        sb.append(", execUser=").append(execUser);
        sb.append(", start_time=").append(start_time);
        sb.append(", end_time=").append(end_time);
        sb.append(", work_status_id=").append(work_status_id);
        sb.append(", workStatus=").append(workStatus);
        sb.append(", workMsgList=").append(workMsgList);
        sb.append('}');
        return sb.toString();
    }

    public User getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(User publishUser) {
        this.publishUser = publishUser;
    }

    public User getExecUser() {
        return execUser;
    }

    public void setExecUser(User execUser) {
        this.execUser = execUser;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public List<WorkMsg> getWorkMsgList() {
        return workMsgList;
    }

    public void setWorkMsgList(List<WorkMsg> workMsgList) {
        this.workMsgList = workMsgList;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPublish_user_id() {
        return publish_user_id;
    }

    public void setPublish_user_id(Integer publish_user_id) {
        this.publish_user_id = publish_user_id;
    }

    public Integer getExec_user_id() {
        return exec_user_id;
    }

    public void setExec_user_id(Integer exec_user_id) {
        this.exec_user_id = exec_user_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getWork_status_id() {
        return work_status_id;
    }

    public void setWork_status_id(Integer work_status_id) {
        this.work_status_id = work_status_id;
    }
}
