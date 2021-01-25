package com.xboot.xtest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;

import com.xboot.config.exception_log.BizException;
import com.xboot.config.exception_log.WebLog;
import com.xboot.xtest.cache.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Api(tags = "logtest", description = "log error范例")
@RequestMapping("/logtest")
public class LogTest {

    private final static Logger logger = LoggerFactory.getLogger(LogTest.class);
    @ApiOperation(value = "错误")
    @RequestMapping("/error")
    @WebLog("测试 error")
   public String error(ModelMap map) {
        map.addAttribute("userName", "ityouknow:");
        int i=1/0;
        return "test/thymeleaf/string";
    }

    @ApiOperation(value = "错误2")
    @RequestMapping("/error2")
    @WebLog("测试 error2")
    public String error2(ModelMap map) {

        if (true)
        throw new BizException(404,"没有此用户");
        
        return "test/thymeleaf/string";
    }

    @RequestMapping("/log")
    @WebLog("测试 log info")
    public String log(ModelMap map) {
    	String s="test log 哈哈哈哈哈哈!";
    	logger.info(s);
    	logger.error(s);
    	logger.warn (s);
    	 map.addAttribute("userName", s);
        return "test/thymeleaf/string";
    }


}