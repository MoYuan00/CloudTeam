package com.zq.mvc.main.controls.work;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/work")
public class UpdateWorkExecUserIdControl {
    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkManager serviceWorkManager;

    @ResponseBody
    @RequestMapping(path = "/updateWorkExecUserId")
    ResponseBean exec(Integer workId, Integer userId){

        serviceWorkManager.updateExecUserId(workId, userId);
        return responseBean;
    }

}
