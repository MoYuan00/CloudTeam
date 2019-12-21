package com.zq.mvc.main.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WorkMsg {
    private Integer work_msg_id;
    private Integer report_user_id;
    private User reportUser;
    private Integer work_id;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date report_time;
    private String content_text;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WorkMsg{");
        sb.append("work_msg_id=").append(work_msg_id);
        sb.append(", report_user_id=").append(report_user_id);
        sb.append(", reportUser=").append(reportUser);
        sb.append(", work_id=").append(work_id);
        sb.append(", report_time=").append(report_time);
        sb.append(", content_text='").append(content_text).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public User getReportUser() {
        return reportUser;
    }

    public void setReportUser(User reportUser) {
        this.reportUser = reportUser;
    }

    public Integer getWork_msg_id() {
        return work_msg_id;
    }

    public void setWork_msg_id(Integer work_msg_id) {
        this.work_msg_id = work_msg_id;
    }

    public Integer getReport_user_id() {
        return report_user_id;
    }

    public void setReport_user_id(Integer report_user_id) {
        this.report_user_id = report_user_id;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public String getContent_text() {
        return content_text;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text;
    }
}
