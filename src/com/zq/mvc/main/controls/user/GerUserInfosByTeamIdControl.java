package com.zq.mvc.main.controls.user;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.user.ServiceUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GerUserInfosByTeamIdControl {
    @Autowired
    private ServiceUserManager serviceUserManager;

    @Autowired
    ResponseBean responseBean;

    @RequestMapping(path = "getUserInfoByTeamId")
    @ResponseBody
    private ResponseBean exec(Integer teamId){
        serviceUserManager.getUserByTeam_id(teamId);
        return responseBean;
    }
}
