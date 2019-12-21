package com.zq.mvc.main.controls.work;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeleteWorkByWorkIdControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkManager serviceWorkManager;

    @RequestMapping(path = "deleteWorkByWorkId")
    @ResponseBody
    ResponseBean exec(Integer workId){
        serviceWorkManager.deleteWork(workId);
        return responseBean;
    }

}
