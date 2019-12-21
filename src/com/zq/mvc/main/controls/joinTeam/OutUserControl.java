package com.zq.mvc.main.controls.joinTeam;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.joinTeam.ServiceJoinTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/joinTeam")
public class OutUserControl {
    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceJoinTeamManager serviceJoinTeamManager;

    @ResponseBody
    @RequestMapping(path = "/outUser")
    ResponseBean exec(Integer userId, Integer teamId){
        serviceJoinTeamManager.removeUserJoinTeam(userId, teamId);
        return responseBean;
    }


}
