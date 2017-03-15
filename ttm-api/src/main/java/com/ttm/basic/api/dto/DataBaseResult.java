package com.ttm.basic.api.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * Created by liguoqing on 2016/9/6.
 */
@JacksonXmlRootElement(localName = "root")
public class DataBaseResult<T> implements Serializable{

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误码
     */
    private int errorCode = 0;

    /**
     * 成功标识
     */
    private boolean success = true;

    /**
     * 数据对象
     */
    private T model;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "DataBaseResult{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                ", success=" + success +
                ", model=" + model +
                '}';
    }
}
