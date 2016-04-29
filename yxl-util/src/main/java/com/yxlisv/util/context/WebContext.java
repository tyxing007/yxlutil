package com.yxlisv.util.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Web相关上下文</p>
 * @author 杨雪令
 * @time 2016年3月17日下午4:38:46
 * @version 1.0
 */
public class WebContext {

	//httpServletRequest ThreadLocal
	private static final ThreadLocal<HttpServletRequest> httpServletRequest = new ThreadLocal<HttpServletRequest>();
	//httpServletResponse ThreadLocal
	private static final ThreadLocal<HttpServletResponse> httpServletResponse = new ThreadLocal<HttpServletResponse>();
	
	/**
	 * <p>获取当前HttpServletRequest对象</p>
	 * @return request HttpServletRequest对象 
	 * @author 杨雪令
	 * @time 2016年3月17日下午4:42:41
	 * @version 1.0
	 */
	public static HttpServletRequest getServletRequest(){
		return httpServletRequest.get();
	}
	
	/**
	 * <p>设置当前HttpServletRequest对象</p>
	 * @param request HttpServletRequest对象 
	 * @author 杨雪令
	 * @time 2016年3月17日下午4:42:41
	 * @version 1.0
	 */
	public static void setServletRequest(HttpServletRequest request){
		httpServletRequest.set(request);
	}
	
	
	/**
	 * <p>获取当前HttpServletResponse对象</p>
	 * @return request HttpServletResponse对象 
	 * @author 杨雪令
	 * @time 2016年3月17日下午4:42:41
	 * @version 1.0
	 */
	public static HttpServletResponse getServletResponse(){
		return httpServletResponse.get();
	}
	
	/**
	 * <p>设置当前HttpServletResponse对象</p>
	 * @param request HttpServletResponse对象 
	 * @author 杨雪令
	 * @time 2016年3月17日下午4:42:41
	 * @version 1.0
	 */
	public static void setServletResponse(HttpServletResponse request){
		httpServletResponse.set(request);
	}
}