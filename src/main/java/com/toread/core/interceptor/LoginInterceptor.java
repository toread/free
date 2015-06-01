/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-04
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.interceptor;

import com.toread.common.config.SessionConfig;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lizhibing
 * @ClassName: LoginInterceptor
 * @Description:
 * @date 2015-05-04 15:27
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        Boolean flag = true;
        HttpSession session = request.getSession();
        if(!(url.startsWith("/public")||url.startsWith("/js"))){
            if(session.getAttribute(SessionConfig.ACCOUNT_SESSION)==null){
                flag =  false;
            }
        }
        return flag;
    }

    /**
     * 是否制定的url地址是否跳过验证
     * @param url
     * @return
     */
    protected Boolean skipValidate(String url){
        Boolean validate = false;
        return  false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
