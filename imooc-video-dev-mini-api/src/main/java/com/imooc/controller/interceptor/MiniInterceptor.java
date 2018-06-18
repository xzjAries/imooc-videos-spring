package com.imooc.controller.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;

public class MiniInterceptor implements HandlerInterceptor {
	@Autowired
	public RedisOperator redis;

	public static final String USER_REDIS_SESSION = "user_redis_session";

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		

		String headerUserId = request.getHeader("headerUserId");
		String headerUserToken = request.getHeader("headerUserToken");

		if (StringUtils.isNoneBlank(headerUserId) && StringUtils.isNoneBlank(headerUserToken)) {
			String uniqueToken = redis.get(USER_REDIS_SESSION + ":" + headerUserId);
			if (StringUtils.isBlank(headerUserId) || StringUtils.isEmpty(headerUserId)) {
				System.out.println("请登录...");
				returnErrorResponse(response,new IMoocJSONResult().errorTokenMsg("请登录..."));
				
				return false;
			}else {
				if(!headerUserToken.equals(uniqueToken)) {
					System.out.println("账号在别的机子上登录...");
					returnErrorResponse(response,new IMoocJSONResult().errorTokenMsg("账号被挤出..."));
					return false;
				}
			}
			System.out.println("请求拦截...");
		} else {
			System.out.println("请登录...");
			returnErrorResponse(response,new IMoocJSONResult().errorTokenMsg("请登录..."));
			return false;
		}
		
		/**
		 * 返回false:请求被拦截,返回 返回true:请求ok,可以继续执行，放行
		 */
		return true;

	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
	 * （主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
	
	public void returnErrorResponse(HttpServletResponse response, IMoocJSONResult result) 
			throws IOException, UnsupportedEncodingException {
		OutputStream out=null;
		try{
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}
	

}
