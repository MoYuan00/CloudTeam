package com.zq.mvc.main.controls.team;


import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeamGetByTeamIdControl {
    @Autowired
    ServiceTeamManager serviceTeamManager;

    @Autowired
    ResponseBean responseBean;

    @RequestMapping(path = "getTeamInfoByTeamId")
    @ResponseBody
    private ResponseBean exec(Integer teamId){

        serviceTeamManager.getTeamAllInfo(null, teamId);

        return responseBean;
    }

}
