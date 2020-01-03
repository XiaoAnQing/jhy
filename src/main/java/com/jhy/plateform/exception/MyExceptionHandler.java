package com.jhy.plateform.exception;

import com.jhy.plateform.utils.JsonModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    //全局异常处理

    @ExceptionHandler
    public JsonModel handlerParamException(ValidateException kpException){
        JsonModel jsonModel = new JsonModel();
        jsonModel.setMsg(kpException.getMessage());
        return jsonModel;
    }
}
