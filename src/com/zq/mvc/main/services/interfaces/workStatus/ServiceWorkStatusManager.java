package com.zq.mvc.main.services.interfaces.workStatus;


import com.zq.mvc.main.dto.ResponseBean;


public interface ServiceWorkStatusManager {

    /**
     * 获取所有的任务状态
     * @return
     */
    ResponseBean getWorkStatuses();

}
