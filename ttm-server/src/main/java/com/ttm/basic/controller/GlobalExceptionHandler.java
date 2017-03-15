package com.ttm.basic.controller;

import com.ttm.basic.api.dto.DataBaseResult;
import com.ttm.basic.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liguoqing on 2017/3/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public DataBaseResult<?> exceptionHandler(Throwable e) {
        if (e instanceof GlobalException) {
            logger.warn("GlobalExceptionHandler.exceptionHandler e {}", e.getMessage());
            DataBaseResult result = new DataBaseResult();
            result.setErrorCode(100);
            result.setErrorMsg(e.getMessage());
            result.setModel(null);
            result.setSuccess(false);
            return result;
        }
        return null;
    }

}
