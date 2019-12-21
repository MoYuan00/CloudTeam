package com.zq.mvc.main.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class JoinTeam extends Team {
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date join_time;

    private Integer user_id;

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JoinTeam{");
        sb.append("join_time=").append(join_time);
        sb.append(", user_id=").append(user_id);
        sb.append('}');
        return sb.toString();
    }
}
