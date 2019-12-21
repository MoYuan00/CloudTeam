package com.zq.mvc.main.controls.work;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/work")
public class UpdateWorkWorkStatusIdControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkManager serviceWorkManager;

    Logger logger = Logger.getLogger(UpdateWorkWorkStatusIdControl.class);

    @RequestMapping(path = "/updateWorkWorkStatusId")
    @ResponseBody
    ResponseBean exec(Integer workId, Integer workStatusId){
        logger.info("当前要更新的任务状态: " + workId + " work_status_id:" + workStatusId);
        serviceWorkManager.updateWorkStatusId(workId, workStatusId);
        return responseBean;
    }

}
