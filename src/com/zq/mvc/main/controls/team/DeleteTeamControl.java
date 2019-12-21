package com.zq.mvc.main.controls.team;

import com.zq.mvc.main.dto.ErrNo;
import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.Team;
import com.zq.mvc.main.pojo.User;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping(path = "/team")
public class DeleteTeamControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceTeamManager serviceTeamManager;

    @ResponseBody
    @RequestMapping(path = "/deleteTeam")
    ResponseBean exec(@SessionAttribute("user")User user, Integer teamId){
        if(user == null){// 当前没有登录
            return responseBean.setErrNo(ErrNo.noLoginUser);
        }
        serviceTeamManager.deleteTeam(user.getUser_id(), teamId);
        return responseBean;
    }


}
