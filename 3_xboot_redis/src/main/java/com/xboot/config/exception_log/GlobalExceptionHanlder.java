package com.xboot.config.exception_log;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, HttpServletRequest request) {
     
    	log.error("Exception ",ex);
        return ResponseEntity.ok(new JsonResult(500,ex.getMessage(),request.getRequestURL().toString()));
    }
    
    @ExceptionHandler(BizException.class)
    public ResponseEntity handleBizException(BizException ex, HttpServletRequest request){
        log.error("BizException ",ex);
        return ResponseEntity.ok(new JsonResult(ex.getCode(),ex.getMessage(),request.getRequestURL().toString()));
    }

    public static String toUTF8(String s) throws UnsupportedEncodingException{
    	byte[] utf8Bytes = s.getBytes("UTF-8"); 
    	//然后用utf-8 对这个字节数组解码成新的字符串
    	s = new String(utf8Bytes, "UTF-8");
    	
    	return s;
    }
    
}

