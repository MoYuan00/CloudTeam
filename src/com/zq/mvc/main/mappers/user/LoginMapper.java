package com.zq.mvc.main.mappers.user;

import com.zq.mvc.main.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    public User loginByNameAndPassword(
            @Param("userName") String userName,
            @Param("password") String password);

}
