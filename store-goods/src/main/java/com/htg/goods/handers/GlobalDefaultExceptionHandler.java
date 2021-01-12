package com.htg.goods.handers;

import com.htg.common.exception.BizExcp;
import com.htg.common.exception.DaoExcp;
import com.htg.common.result.CodeEnum;
import com.htg.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一拦截异常
 * 参考:
 * https://www.jianshu.com/p/764eaf6c0afe
 * <p>
 * <p>
 * https://blog.csdn.net/qq_36922927/article/details/82026683
 **/
@Slf4j
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param
     * @return
     */
    /* JSON请求参数异常 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult handleValidationBodyException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            log.debug("json param error is ===>{}", fieldErrors);
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                map.put(field, message);
            }
        }
        return CommonResult.error(CodeEnum.PARAM_ERROR, map);
    }


    /* 表单请求参数异常 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public CommonResult handleValidationBodyException(BindException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            log.debug("form param error is ===>{}", fieldErrors);
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                map.put(field, message);
            }
        }
        return CommonResult.error(CodeEnum.PARAM_ERROR, map);
    }

    /* path */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class})
    public CommonResult handleGlobalException(ConstraintViolationException e) {
        return CommonResult.error(CodeEnum.PARAM_ERROR.getCode(), e.getMessage());
    }

    /*业务异常 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BizExcp.class})
    public CommonResult handleGlobalException(BizExcp e) {
        return CommonResult.error(e.getCode(), e.getMsg());
    }


    /*DAO数据异常 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({DaoExcp.class})
    public CommonResult handleDaoExcp(DaoExcp e) {
        return CommonResult.error(CodeEnum.DATA_BASE_ERROR.getCode(), CodeEnum.DATA_BASE_ERROR.getMsg());
    }

}