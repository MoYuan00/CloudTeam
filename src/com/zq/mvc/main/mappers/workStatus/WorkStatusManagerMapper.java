package com.zq.mvc.main.mappers.workStatus;


import com.zq.mvc.main.pojo.Work;
import com.zq.mvc.main.pojo.WorkStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkStatusManagerMapper {

    List<WorkStatus> getWorkStatuses();

    WorkStatus getWorkStatusByWorkStatusId(Integer work_status_id);

}
