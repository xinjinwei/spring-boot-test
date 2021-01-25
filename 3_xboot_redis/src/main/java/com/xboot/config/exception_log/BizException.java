package com.xboot.config.exception_log;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常
 */
@Data
@NoArgsConstructor
public class BizException extends RuntimeException {

    private Integer code;
    private String msg;

    public BizException(Integer code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}

