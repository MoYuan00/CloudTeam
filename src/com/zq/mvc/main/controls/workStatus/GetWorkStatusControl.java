package com.zq.mvc.main.controls.workStatus;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.workStatus.ServiceWorkStatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetWorkStatusControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkStatusManager serviceWorkStatusManager;

    @ResponseBody
    @RequestMapping(path = "getWorkStatus")
    ResponseBean exec(){
        serviceWorkStatusManager.getWorkStatuses();
        return responseBean;
    }


}
