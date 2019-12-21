package com.zq.mvc.main.services._implements.workStatus;

import com.zq.mvc.main.dto.ResponseBean;
import com.zq.mvc.main.mappers.workStatus.WorkStatusManagerMapper;
import com.zq.mvc.main.services.interfaces.workStatus.ServiceWorkStatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkStatusManagerImpl implements ServiceWorkStatusManager {

    @Autowired
    WorkStatusManagerMapper workStatusManagerMapper;

    @Autowired
    ResponseBean responseBean;

    @Override
    public ResponseBean getWorkStatuses() {
        return responseBean.addData("workStatus", workStatusManagerMapper.getWorkStatuses());
    }
}
