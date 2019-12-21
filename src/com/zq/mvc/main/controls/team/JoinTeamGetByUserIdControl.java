package com.zq.mvc.main.controls.team;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JoinTeamGetByUserIdControl {
    @Autowired
    ServiceTeamManager serviceTeamManager;

    @Autowired
    ResponseBean responseBean;

    @ResponseBody
    @RequestMapping(path = "getJoinTeamsByUserId")
    private ResponseBean exec(Integer userId){
        serviceTeamManager.getJoinTeamByUser_id(userId);
        return responseBean;
    }



}
