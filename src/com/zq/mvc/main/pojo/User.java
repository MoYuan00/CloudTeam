package com.zq.mvc.main.pojo;



import java.util.List;
import java.util.Objects;

public class User {
    private Integer user_id;
    private String name;
    private String password;
    private String detail;
    private String role;
    /**
     *  一个用户可以创建多个团队，1对多级联
     */
    private List<Team> createTeamList;
    /**
     * 一个用户可以加入多个团队，每个团队有多个用户加入，多对多级联
     */
    private List<JoinTeam> joinTeamList;
    /**
     *  一个用户可以发布多个任务
     */
    private List<Work> reportWorkList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id.equals(user.user_id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("user_id='").append(user_id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", createTeamList=").append(createTeamList);
        sb.append(", joinTeamList=").append(joinTeamList);
        sb.append(", reportWorkList=").append(reportWorkList);
        sb.append('}');
        return sb.toString();
    }
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Team> getCreateTeamList() {
        return createTeamList;
    }

    public void setCreateTeamList(List<Team> createTeamList) {
        this.createTeamList = createTeamList;
    }

    public List<JoinTeam> getJoinTeamList() {
        return joinTeamList;
    }

    public void setJoinTeamList(List<JoinTeam> joinTeamList) {
        this.joinTeamList = joinTeamList;
    }

    public List<Work> getReportWorkList() {
        return reportWorkList;
    }

    public void setReportWorkList(List<Work> reportWorkList) {
        this.reportWorkList = reportWorkList;
    }
}
