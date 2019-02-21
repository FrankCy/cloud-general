package com.spring.cloud.common.intercepter;

import com.spring.cloud.common.context.UserContextHolder;
import com.spring.cloud.common.util.HttpConvertUtil;
import com.spring.cloud.common.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description：用户上下文拦截器
 * @version 1.0
 * @author: Yang.Chang
 * @email: cy880708@163.com
 * @date: 2018/11/5 上午11:40
 * @mofified By:
 */
public class UserContextInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2) throws Exception {
		log.info("进入拦截器 —————————— 1");
		User user = new User(HttpConvertUtil.httpRequestToMap(request));
//		if(StringUtils.isEmpty(user.getUserId()) && StringUtils.isEmpty(user.getUserName())) {
//			log.error("the user is null, please access from gateway or check user info");
//			return false;
//		}
		UserContextHolder.set(user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2, ModelAndView arg3)
			throws Exception {
		log.info("进入拦截器 —————————— 2");
		// DOING NOTHING
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse respone, Object arg2, Exception arg3)
			throws Exception {
		log.info("进入拦截器 —————————— 3");
		UserContextHolder.shutdown();
	}
	
	
	
}
