package com.zq.mvc.main.controls.workMsg;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.WorkMsg;
import com.zq.mvc.main.services.interfaces.workMsg.ServiceWorkMsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppendWorkMsgControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkMsgManager serviceWorkMsgManager;

    @ResponseBody
    @RequestMapping(path = "appendWorkMsg")
    ResponseBean exec(@RequestBody WorkMsg workMsg){
        serviceWorkMsgManager.appendWorkMsg(workMsg);
        return responseBean;
    }

}
