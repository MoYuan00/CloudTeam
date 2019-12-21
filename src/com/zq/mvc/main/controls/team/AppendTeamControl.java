package com.zq.mvc.main.controls.team;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.Team;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping(path = "/team")
public class AppendTeamControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceTeamManager serviceTeamManager;

    @RequestMapping(path = "/appendTeam")
    @ResponseBody
    ResponseBean exec(@SessionAttribute(name = "user") User user,
                      @RequestBody Team team){
        // TODO 这里需要添加检查是否登录的逻辑，没登录应该跳出
        serviceTeamManager.appendTeamService(user.getUser_id(), team);
        return responseBean;
    }


}
