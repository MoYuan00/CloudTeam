package com.zq.mvc.main.controls.work;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetWorksControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkManager serviceWorkManager;

    Logger logger = Logger.getLogger(GetWorksControl.class);

    @ResponseBody
    @RequestMapping(path = "getWorks")
    ResponseBean exec(@RequestParam Integer userId, Integer teamId){
        logger.info("userId: " + userId + " teamId" + teamId);
        serviceWorkManager.getTeamWorks(userId, teamId);
        return responseBean;
    }

}
