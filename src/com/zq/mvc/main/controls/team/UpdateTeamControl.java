package com.zq.mvc.main.controls.team;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.Team;
import com.zq.mvc.main.services.interfaces.team.ServiceTeamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/team")
public class UpdateTeamControl {
    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceTeamManager serviceTeamManager;

    @RequestMapping(path = "/updateTeam")
    @ResponseBody
    ResponseBean exec(@RequestBody Team team){
        serviceTeamManager.updateTeamSomeInfo(team);
        return responseBean;
    }

}
