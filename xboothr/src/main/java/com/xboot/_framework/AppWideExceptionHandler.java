package com.xboot._framework;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.RequestContext;


import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;




@ControllerAdvice
public class AppWideExceptionHandler {


	// 从后台代码获取国际化信息 requestContext.getMessage("v.o_add.success")
	@ModelAttribute
	public RequestContext newRequestContext(HttpServletRequest request) {
//		System.out.println("============放入Model RequestContext");
//		request.getSession(true).setAttribute("lastRequestUrl", request.getRequestURL());
//		System.out.println(request.getRequestURL());
		return new RequestContext(request);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		System.out.println("============放入Model RequestContext");
		//日期类型转换
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat1, true));
		
		//时间类型转换
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		binder.registerCustomEditor(Time.class, new CustomDateEditor(dateFormat2, true));
		
		//日期时间类型转换
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(dateFormat3, true));

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(reason = "服务器内部错误", value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String allException(Exception e) {
		e.printStackTrace();
		System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");
		return "error/duplicate";
	}

	/**
	 * 读取国际化文件里面的变量值
	 * 
	 * @param msgCode
	 *            变量名称
	 * @param args
	 *            参数
	 * @return
	 */
	// public static String loadMessage(String msgCode, Object... args) {
	// ReloadableResourceBundleMessageSource messageSource;
	// messageSource = new ReloadableResourceBundleMessageSource();
	// messageSource.setBasename("classpath:/messages");
	// messageSource.setUseCodeAsDefaultMessage(true);
	// return messageSource.getMessage(msgCode, args, Locale.getDefault());
	// }
	//
}
