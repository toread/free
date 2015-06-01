package com.toread.core.interceptor.exception;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-4-22.
 * @author Administrator
 * @version 1.0
 * @created 17-八月-2014 11:47:53
 */
public class JsonNormInterceptor extends HandlerInterceptorAdapter {

	public JsonNormInterceptor(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @exception Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	  throws Exception{
        ResultNorm resultNorm = new ResultNorm();
        if(ex!=null){
            resultNorm.setSuccess(false);
            List<String> msgList = new ArrayList();
            msgList.add(ex.getMessage());
            resultNorm.setErrorMsg(msgList);
        }else{
            resultNorm.setSuccess(false);
            resultNorm.setResult(handler);
        }
        handler = resultNorm;
	}
}