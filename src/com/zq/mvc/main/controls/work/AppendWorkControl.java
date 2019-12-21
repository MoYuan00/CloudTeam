package com.zq.mvc.main.controls.work;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.pojo.Work;
import com.zq.mvc.main.services.interfaces.work.ServiceWorkManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/work")
public class AppendWorkControl {

    @Autowired
    ResponseBean responseBean;

    @Autowired
    ServiceWorkManager serviceWorkManager;

    static Logger logger = Logger.getLogger(AppendWorkControl.class);

    @ResponseBody
    @RequestMapping(path = "/appendWork")
    ResponseBean exec(@RequestBody Work work){
        logger.info("要添加的任务为: " + work);
        serviceWorkManager.appendWork(work);
        return  responseBean;
    }


}
