package com.han.handler.exception;
import com.han.domain.ResponseResult;
import com.han.enums.AppHttpCodeEnum;
import com.han.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息方便调试
        log.error("出现了异常情况！{}",e);
        //从异常对象中获取提示信息封装
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e){
        //打印异常信息方便调试
        log.error("出现了系统异常情况！{}",e);
        //从异常对象中获取提示信息封装
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
