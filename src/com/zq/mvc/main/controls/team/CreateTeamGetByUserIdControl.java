package com.zq.mvc.main.controls.team;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateTeamGetByUserIdControl {

    @Autowired
    ServiceTeamManager serviceTeamManager;

    @Autowired
    ResponseBean responseBean;

    @ResponseBody
    @RequestMapping(path = "getCreateTeamsByUserId")
    private ResponseBean exec(Integer userId){

        serviceTeamManager.getCreateTeamByUser_id(userId);

        return responseBean;
    }

}
