package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.base.ResponseResult;
import org.example.exception.AppException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一的异常处理：
 * 响应需要返回统一的数据格式（ok=false，message）
 * 自定义异常，直接取异常携带中文错误信息
 * 非自定义异常，转
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public Object handleAppException(AppException e){
        log.debug("自定义异常", e);
        ResponseResult json = new ResponseResult();
        json.setMessage(e.getMessage());
        return json;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        log.error("系统出错啦", e);
        ResponseResult json = new ResponseResult();
        json.setMessage("系统出错了，请联系管理员");
        return json;
    }
}
