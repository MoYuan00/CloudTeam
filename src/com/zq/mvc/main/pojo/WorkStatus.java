package com.zq.mvc.main.pojo;

public class WorkStatus {
    private Integer work_status_id;
    private String status_name;
    private String status_detail;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WorkStatus{");
        sb.append("work_status_id=").append(work_status_id);
        sb.append(", status_name='").append(status_name).append('\'');
        sb.append(", status_detail='").append(status_detail).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public Integer getWork_status_id() {
        return work_status_id;
    }

    public void setWork_status_id(Integer work_status_id) {
        this.work_status_id = work_status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getStatus_detail() {
        return status_detail;
    }

    public void setStatus_detail(String status_detail) {
        this.status_detail = status_detail;
    }

}
