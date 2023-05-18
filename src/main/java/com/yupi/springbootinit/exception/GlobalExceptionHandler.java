package com.yupi.springbootinit.exception;

import cn.hutool.json.JSONException;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.ValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author <a href="https://github.com/Saving66">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    @ExceptionHandler(JSONException.class)
    public BaseResponse<?> jsonExceptionHandler(JSONException e) {
        log.error("JSONException", e);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, "JSON格式错误");
    }

    @ExceptionHandler(ValidationException.class)
    public BaseResponse<?> validationExceptionHandler(ValidationException e) {
        log.error("ValidationException", e);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, "类型不匹配");
    }
}
