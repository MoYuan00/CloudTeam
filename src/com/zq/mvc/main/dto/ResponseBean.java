package com.zq.mvc.main.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给前端的bean
 *
 * @param <T>
 */
@Component
@RequestScope
public class ResponseBean<T extends Object> {
    public ResponseBean() {
        setErrNo(ErrNo.noErr);// 默认为无错误
        data = new HashMap<String, T>();
    }

    /**
     * 错误编号
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullNumberAsZero)
    private Integer errNo;
    /**
     * 响应的类型（success,warn,err）
     */
    private String type;
    /**
     * 提示信息，响应返回的信息（成功，错误，失败，或者更具体的信息）
     */
    private String message;
    /**
     * 响应返回的数据
     * fast json输出字段为Null的两种方式
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private final Map<String, T> data;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseBean{");
        sb.append("errNo=").append(errNo);
        sb.append(", type='").append(type).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public Integer getErrNo() {
        if(errNo != null)
            return errNo;
        return ErrNo.noErr.getErrNo();
    }

    public ResponseBean setErrNo(ErrNo.ErrBean errBean) {
        this.errNo = errBean.getErrNo();
        this.message = errBean.getErrMessage();
        this.type = "error";
        if (errBean.equals(ErrNo.noErr)) type = "success";
        return this;
    }

    public Map<String, T> getData() {
        if (data.size() == 0) return null;
        return data;
    }

    public ResponseBean removeAllData(){
        if(data != null) data.clear();
        return this;
    }

    public String getType() {
        return type;
    }

    public ResponseBean setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBean setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 如果当前错误码为null，自动设置为无错误
     *
     * @param data
     */
    public ResponseBean addData(String name, T data){
        if (errNo == null)
            this.errNo = ErrNo.noErr.getErrNo();
        this.data.put(name, data);
        if(type == null) type = "success";
        return this;
    }

}
